package com.javaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping(value = "/admin/order")
    public String getHomePage() {
        return "/admin/order/home";
    }
}
