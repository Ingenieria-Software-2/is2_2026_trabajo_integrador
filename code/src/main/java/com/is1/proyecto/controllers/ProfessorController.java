package com.is1.proyecto.controllers;

import com.is1.proyecto.models.Professor;
import com.is1.proyecto.models.Role;

import com.is1.proyecto.services.ProfessorService;
import com.is1.proyecto.services.ServiceException;

import com.is1.proyecto.services.dto.ProfessorCreateDTO;

import com.is1.proyecto.validators.UserValidator;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class ProfessorController extends BaseController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService, MustacheTemplateEngine templateEngine) {

        super(templateEngine);
        this.professorService = professorService;
    }

    // -----------------------------------------------------------
    // GET /professor/create
    // -----------------------------------------------------------
    public String showForm(Request req, Response res) {

        Map<String, Object> model = new HashMap<>();
        addFlashMessages(req, model);

        return templateEngine.render(new ModelAndView(model, "professor_form.mustache"));
    }

    // -----------------------------------------------------------
    // POST /professor/new
    // -----------------------------------------------------------
    public String create(Request req, Response res) {

        ProfessorCreateDTO dto = buildDTO(req);

        try {

            UserValidator.validate(dto);
            Professor professor = professorService.createProfessor(dto);
            redirectToSuccess(res, professor.getPerson().getName(), "Profesor creado exitosamente", false);

        } catch (ServiceException e) {

            res.status(e.getStatusCode());
            res.redirect("/professor/create?error=" + encode(e.getMessage()));

        } catch (Exception e) {

            System.out.println("ERROR PROFESOR:");
            e.printStackTrace();

            res.status(500);
            res.redirect("/professor/create?error=" + encode("Error interno. Intente de nuevo."));
        }

        return "";
    }

    // -----------------------------------------------------------
    // DTO Builder
    // -----------------------------------------------------------
    private ProfessorCreateDTO buildDTO(Request req) {

        ProfessorCreateDTO dto =
            new ProfessorCreateDTO();

        dto.dni = req.queryParams("dni");
        dto.name = req.queryParams("name");
        dto.surname = req.queryParams("surname");
        dto.username = req.queryParams("username");
        dto.email = req.queryParams("email");
        dto.cellphone = req.queryParams("cellphone");
        dto.birthdate = req.queryParams("birthdate");
        dto.password = req.queryParams("password");
        dto.degree = req.queryParams("degree");
        dto.university = req.queryParams("university");
        dto.position = req.queryParams("position");
        dto.role = Role.PROFESSOR;

        return dto;
    }
}