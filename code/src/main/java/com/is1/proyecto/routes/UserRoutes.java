package com.is1.proyecto.routes;

import com.is1.proyecto.controllers.AuthController;
import com.is1.proyecto.services.AuthService;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserRoutes {

    private final AuthController controller;

    public UserRoutes() {
        AuthService service = new AuthService();
        MustacheTemplateEngine templateEngine = new MustacheTemplateEngine();
        this.controller = new AuthController(service, templateEngine);
    }

    public void register() {
        get("/",              controller::showLoginForm);
        get("/user/create",   controller::showCreateForm);
        get("/user/created",  controller::showCreatedSuccess);
        get("/dashboard",     controller::showDashboard);
        get("/logout",        controller::logout);
        post("/user/new",     controller::create);
        post("/login",        controller::login);
    }
}