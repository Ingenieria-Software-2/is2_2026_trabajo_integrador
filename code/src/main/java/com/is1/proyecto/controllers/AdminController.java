package com.is1.proyecto.controllers;

import com.is1.proyecto.models.Person;
import com.is1.proyecto.models.Role;
import com.is1.proyecto.services.AuthService;
import com.is1.proyecto.services.ServiceException;
import com.is1.proyecto.services.dto.PersonCreateDTO;
import com.is1.proyecto.services.dto.PersonLoginDTO;
import com.is1.proyecto.validators.UserValidator;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class AdminController extends BaseController {

    private final AuthService authService;

    public AdminController(AuthService authService, MustacheTemplateEngine templateEngine) {

        super(templateEngine);
        this.authService = authService;
    }

    // -----------------------------------------------------------
    // GET /login
    // -----------------------------------------------------------
    public String showLoginForm(Request req, Response res) {

        Map<String, Object> model = new HashMap<>();
        addFlashMessages(req, model);
        return templateEngine.render(new ModelAndView(model, "login.mustache"));
    }

    // -----------------------------------------------------------
    // POST /login
    // -----------------------------------------------------------
    public String login(Request req, Response res) {

        PersonLoginDTO dto = new PersonLoginDTO();

        dto.username = req.queryParams("username");
        dto.password = req.queryParams("password");

        try {

            Person person = authService.login(dto);
            loginUser(req, person);
            res.redirect("/dashboard");

        } catch (ServiceException e) {

            res.status(e.getStatusCode());

            Map<String, Object> model = new HashMap<>();
            model.put("errorMessage", e.getMessage());

            return templateEngine.render(new ModelAndView(model, "login.mustache"));

        } catch (Exception e) {

            res.status(500);

            Map<String, Object> model = new HashMap<>();
            model.put("errorMessage", "Error interno. Intente de nuevo.");

            return templateEngine.render(new ModelAndView(model, "login.mustache"));
        }

        return "";
    }

    // -----------------------------------------------------------
    // GET /dashboard
    // -----------------------------------------------------------
    public String showDashboard(Request req, Response res) {

        String username = req.session().attribute("currentUserUsername");
        Boolean loggedIn = req.session().attribute("loggedIn");

        if (username == null || loggedIn == null || !loggedIn) {
            res.redirect("/login?error=" + encode("Debes iniciar sesión para acceder."));
            return "";
        }

        Map<String, Object> model = new HashMap<>();
        model.put("username", username);

        return templateEngine.render(new ModelAndView(model, "dashboard.mustache"));
    }

    // -----------------------------------------------------------
    // GET /logout
    // -----------------------------------------------------------
    public String logout(Request req, Response res) {

        req.session().invalidate();
        res.redirect("/login");

        return "";
    }

    // -----------------------------------------------------------
    // GET /admin/create
    // -----------------------------------------------------------
    public String showCreateAdminForm(Request req, Response res) {

        Map<String, Object> model = new HashMap<>();

        addFlashMessages(req, model);

        return templateEngine.render(new ModelAndView(model, "user_form.mustache"));
    }

    // -----------------------------------------------------------
    // POST /admin/new
    // -----------------------------------------------------------
    public String createAdmin(Request req, Response res) {

        PersonCreateDTO dto = buildAdminDTO(req);

        try {

            UserValidator.validate(dto);
            Person person = authService.createPerson(dto);
            // Crear sesión para el administrador recién creado
            loginUser(req, person);
            redirectToSuccess(res, person.getName(), "Administrador creado exitosamente", true);

        } catch (ServiceException e) {

            res.status(e.getStatusCode());
            res.redirect("/admin/create?error=" + encode(e.getMessage()));

        } catch (Exception e) {

            res.status(500);
            res.redirect("/admin/create?error=" + encode("Error interno. Intente de nuevo."));
        }

        return "";
    }

    private PersonCreateDTO buildAdminDTO(Request req) {

        PersonCreateDTO dto = new PersonCreateDTO();

        dto.dni = req.queryParams("dni");
        dto.name = req.queryParams("name");
        dto.surname = req.queryParams("surname");
        dto.username = req.queryParams("username");
        dto.email = req.queryParams("email");
        dto.cellphone = req.queryParams("cellphone");
        dto.birthdate = req.queryParams("birthdate");
        dto.password = req.queryParams("password");

        dto.role = Role.ADMIN;

        return dto;
    }
}