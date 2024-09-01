package com.javaweb.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller(value = "productControllerOfClient")
public class ProductController {
    @GetMapping(value = "/product/{id}")
    public String getProductDetailById(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        return "client/product/detail";
    }

}
