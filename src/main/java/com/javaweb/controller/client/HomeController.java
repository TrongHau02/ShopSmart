package com.javaweb.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "homeControllerOfClient")
public class HomeController {
    @GetMapping(value = {"/", "/home"})
    public String getHomePage() {
        return "client/homepage/home";
    }
}
