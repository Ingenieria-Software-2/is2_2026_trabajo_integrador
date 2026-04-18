package com.is1.proyecto.routes;

import com.is1.proyecto.controllers.UserController;
import com.is1.proyecto.services.UserService;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserRoutes {

    private final UserController controller;

    public UserRoutes() {
        UserService service = new UserService();
        MustacheTemplateEngine templateEngine = new MustacheTemplateEngine();
        this.controller = new UserController(service, templateEngine);
    }

    public void register() {
        get("/",              controller::showLoginForm);
        get("/user/create",   controller::showCreateForm);
        get("/dashboard",     controller::showDashboard);
        get("/logout",        controller::logout);
        post("/user/new",     controller::create);
        post("/login",        controller::login);
    }
}