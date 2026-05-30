package com.is1.proyecto.controllers;

import com.is1.proyecto.models.Person;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    protected final MustacheTemplateEngine templateEngine;

    public BaseController(MustacheTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    
    protected void addFlashMessages(Request req, Map<String, Object> model) {

        String success = req.queryParams("message");
        String error = req.queryParams("error");

        if (success != null && !success.isEmpty()) {
            model.put("successMessage", success);
        }

        if (error != null && !error.isEmpty()) {
            model.put("errorMessage", error);
        }
    }

    protected String encode(String value) {

        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported", e);
        }
    }

    protected void loginUser(Request req, Person person) {

        req.session(true).attribute("currentUserUsername", person.getUsername());
        req.session().attribute("userId", person.getId());
        req.session().attribute("loggedIn", true);
    }

    // ----------------------------------------------------------------
    // Vista de éxito reutilizable
    // ----------------------------------------------------------------
    public String showUserCreated(Request req, Response res) {

        Map<String, Object> model = new HashMap<>();
        model.put("name", req.queryParams("name"));
        model.put("message", req.queryParams("message"));
        boolean loggedIn = "true".equals(req.queryParams("loggedIn"));
        model.put("loggedIn", loggedIn);

        return templateEngine.render(new ModelAndView(model, "user_created.mustache"));
    }

    // ----------------------------------------------------------------
    // Redirect reutilizable
    // ----------------------------------------------------------------
    protected void redirectToSuccess(Response res, String name, String message, boolean loggedIn) {

        res.redirect("/user/created" + "?name=" + encode(name) + "&message=" + encode(message) + "&loggedIn=" + loggedIn);
    }
}