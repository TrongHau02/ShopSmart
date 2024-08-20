package com.javaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "/admin"})
    public String getHomePage() {
        return "/admin/dashboard/home";
    }
}
