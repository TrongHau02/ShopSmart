package com.javaweb.controller.client;

import com.javaweb.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Pageable pageable = PageRequest.of(0, 10);
        model.addAttribute("products", this.productService.findAllProduct(pageable).getContent());
        return "client/homepage/home";
    }
}
