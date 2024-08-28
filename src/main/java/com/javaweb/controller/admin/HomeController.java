package com.javaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
    @GetMapping(value = {"/admin/home"})
    public String getHomePage() {
        return "admin/dashboard/home";
    }
}
