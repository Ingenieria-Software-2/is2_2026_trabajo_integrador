package com.is1.proyecto.controllers;

import com.is1.proyecto.services.ProfessorService;
import com.is1.proyecto.services.ServiceException;
import com.is1.proyecto.services.dto.ProfessorCreateDTO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ProfessorController {

    private final ProfessorService professorService;
    private final MustacheTemplateEngine templateEngine;

    // -----------------------------------------------------------
    // Recibe el service por constructor (facilita tests después)
    // -----------------------------------------------------------
    public ProfessorController(ProfessorService professorService,
                               MustacheTemplateEngine templateEngine) {
        this.professorService = professorService;
        this.templateEngine = templateEngine;
    }

    // -----------------------------------------------------------
    // GET /professor/create
    // Solo renderiza el formulario, opcionalmente con mensajes.
    // -----------------------------------------------------------
    public String showForm(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        addFlashMessages(req, model);
        return templateEngine.render(
            new ModelAndView(model, "professor_form.mustache"));
    }

    // -----------------------------------------------------------
    // POST /professor/new
    // Arma el DTO, llama al service, maneja éxito y errores.
    // -----------------------------------------------------------
    public String create(Request req, Response res) {
        ProfessorCreateDTO dto = buildDTO(req);

        try {
            professorService.createProfessor(dto);

            res.status(201);
            res.redirect("/professor/create?message="
                + encode("Profesor " + dto.nombre + " " + dto.apellido
                         + " registrado exitosamente."));

        } catch (ServiceException e) {
            // Error de negocio: el service ya decidió el status code
            res.status(e.getStatusCode());
            res.redirect("/professor/create?error=" + encode(e.getMessage()));

        } catch (Exception e) {
            // Error técnico inesperado
            res.status(500);
            res.redirect("/professor/create?error="
                + encode("Error interno. Intente de nuevo."));
        }

        return "";
    }

    // -----------------------------------------------------------
    // Construye el DTO desde los params del request
    // -----------------------------------------------------------
    private ProfessorCreateDTO buildDTO(Request req) {
        ProfessorCreateDTO dto = new ProfessorCreateDTO();
        dto.nombre      = req.queryParams("nombre");
        dto.apellido    = req.queryParams("apellido");
        dto.mail        = req.queryParams("mail");
        dto.dniStr      = req.queryParams("dni");
        dto.legajoStr   = req.queryParams("legajo");
        dto.titulo      = req.queryParams("titulo");
        dto.universidad = req.queryParams("universidad");
        dto.cargo       = req.queryParams("cargo");
        return dto;
    }

    // -----------------------------------------------------------
    // Lee mensajes de éxito/error de query params y los pasa al modelo
    // -----------------------------------------------------------
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