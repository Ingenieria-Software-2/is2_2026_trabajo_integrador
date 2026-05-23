package com.is1.proyecto.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.is1.proyecto.models.User;
import com.is1.proyecto.services.ServiceException;
import com.is1.proyecto.services.AdminService;
import com.is1.proyecto.services.dto.PersonCreateDTO;
import com.is1.proyecto.services.dto.PersonLoginDTO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.mustache.MustacheTemplateEngine;

public class AdminController {
    
    private final AdminService adminService;
    private final MustacheTemplateEngine templateEngine;

    public AdminController(AdminService adminService, MustacheTemplateEngine templateEngine) {
        this.adminService = adminService;
        this.templateEngine = templateEngine;
    }

 
}
