package com.is1.proyecto.routes;

import com.is1.proyecto.controllers.AdminController;
import com.is1.proyecto.services.AuthService;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserRoutes {

    private final AdminController controller;

    public UserRoutes() {
        AuthService service = new AuthService();
        MustacheTemplateEngine templateEngine = new MustacheTemplateEngine();
        this.controller = new AdminController(service, templateEngine);
    }

    public void register() {
        get("/",              controller::showLoginForm);
        get("/login",         controller::showLoginForm);
        get("/user/create",   controller::showCreateAdminForm);
        get("/user/created",  controller::showUserCreated);
        get("/dashboard",     controller::showDashboard);
        get("/logout",        controller::logout);
        post("/user/new",     controller::createAdmin);
        post("/login",        controller::login);
    }
}