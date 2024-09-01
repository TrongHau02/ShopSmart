package com.javaweb.controller.client;

import com.javaweb.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "homeControllerOfClient")
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Model model) {
        model.addAttribute("products", this.productService.findAllProduct());
        return "client/homepage/home";
    }
}
