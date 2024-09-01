package com.javaweb.controller.client;

import com.javaweb.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller(value = "productControllerOfClient")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{id}")
    public String getProductDetailById(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("productDetail", this.productService.findProductById(id));
        return "client/product/detail";
    }

}
