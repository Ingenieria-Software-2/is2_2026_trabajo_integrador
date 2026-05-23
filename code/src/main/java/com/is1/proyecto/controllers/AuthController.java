package com.is1.proyecto.controllers;

import com.is1.proyecto.models.User;
import com.is1.proyecto.services.ServiceException;
import com.is1.proyecto.services.AuthService;
import com.is1.proyecto.services.dto.PersonCreateDTO;
import com.is1.proyecto.services.dto.PersonLoginDTO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AuthController {

    private final AuthService authService;
    private final MustacheTemplateEngine templateEngine;

    public AuthController(AuthService authService,
                          MustacheTemplateEngine templateEngine) {
        this.authService = authService;
        this.templateEngine = templateEngine;
    }
    //--------------------------------------------------------------
    // GET /user CreatedMuesrta el mensaje de exito al crear usuario
    //---------------------------------------------------------------
    public String showCreatedSuccess(Request req, Response res) {
    String name = req.queryParams("name");
    Map<String, Object> model = new HashMap<>();
    model.put("name", name != null ? name : "Usuario");
    return templateEngine.render(new ModelAndView(model, "user_created.mustache"));
}
    // -----------------------------------------------------------
    // GET /user/create — muestra el formulario de registro
    // -----------------------------------------------------------
    public String showCreateForm(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        addFlashMessages(req, model);
        return templateEngine.render(
            new ModelAndView(model, "user_form.mustache"));
    }

    // -----------------------------------------------------------
    // GET / — muestra el formulario de login Crear Controlador para vistas que no necesitan req res...
    // -----------------------------------------------------------
    public String showLoginForm(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        addFlashMessages(req, model);
        return templateEngine.render(
            new ModelAndView(model, "login.mustache"));
    }

    // -----------------------------------------------------------
    // GET /dashboard — panel principal, requiere sesión activa
    // -----------------------------------------------------------
    public String showDashboard(Request req, Response res) {
        String username = req.session().attribute("currentUserUsername");
        Boolean loggedIn = req.session().attribute("loggedIn");

        if (username == null || loggedIn == null || !loggedIn) {
            res.redirect("/login?error="
                + encode("Debes iniciar sesión para acceder."));
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        return templateEngine.render(
            new ModelAndView(model, "dashboard.mustache"));
    }

    // -----------------------------------------------------------
    // POST /user/new — procesa el registro
    // -----------------------------------------------------------
    public String create(Request req, Response res) {
        PersonCreateDTO dto = new PersonCreateDTO();
        dto.name     = req.queryParams("name");
        dto.password = req.queryParams("password");

    try {
        authService.createUser(dto);
        req.session(true).attribute("currentUserUsername", dto.name);
        req.session().attribute("loggedIn", true);
        res.redirect("/user/created?name=" + encode(dto.name));

    } catch (ServiceException e) {
        res.status(e.getStatusCode());
        res.redirect("/user/create?error=" + encode(e.getMessage()));

    } catch (Exception e) {
        res.status(500);
        res.redirect("/user/create?error=" + encode("Error interno. Intente de nuevo."));
    }

        return "";
    }

    // -----------------------------------------------------------
    // POST /login — procesa la autenticación
    // -----------------------------------------------------------
    public String login(Request req, Response res) {
        PersonLoginDTO dto = new PersonLoginDTO();
        dto.username = req.queryParams("username");
        dto.password = req.queryParams("password");

        try {
            User user = authService.login(dto);

            // Sesión exitosa: guardamos los datos necesarios
            req.session(true).attribute("currentUserUsername", user.getName());
            req.session().attribute("userId", user.getId());
            req.session().attribute("loggedIn", true);

            Map<String, Object> model = new HashMap<>();
            model.put("username", user.getName());
            return templateEngine.render(
                new ModelAndView(model, "dashboard.mustache"));

        } catch (ServiceException e) {
            res.status(e.getStatusCode());
            Map<String, Object> model = new HashMap<>();
            model.put("errorMessage", e.getMessage());
            return templateEngine.render(
                new ModelAndView(model, "login.mustache"));

        } catch (Exception e) {
            res.status(500);
            Map<String, Object> model = new HashMap<>();
            model.put("errorMessage", "Error interno. Intente de nuevo.");
            return templateEngine.render(
                new ModelAndView(model, "login.mustache"));
        }
    }

    // -----------------------------------------------------------
    // GET /logout — invalida la sesión
    // -----------------------------------------------------------
    public String logout(Request req, Response res) {
        req.session().invalidate();
        res.redirect("/");
        return null;
    }

    private void addFlashMessages(Request req, Map<String, Object> model) {
        String success = req.queryParams("message");
        String error   = req.queryParams("error");
        if (success != null && !success.isEmpty()) model.put("successMessage", success);
        if (error   != null && !error.isEmpty())   model.put("errorMessage", error);
    }

    private String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported", e);
        }
    }
}