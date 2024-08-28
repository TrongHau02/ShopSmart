package com.javaweb.controller.admin;

import com.javaweb.domain.Product;
import com.javaweb.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    private final UploadService uploadService;

    public ProductController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping(value = "/admin/product")
    public String getProductPage() {
        return "admin/product/home";
    }

    @GetMapping(value = {"/admin/product/create"})
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping(value = "/admin/product/create")
    public String createProduct(@ModelAttribute("newProduct") Product product, @RequestParam("imageFile") MultipartFile file) {
        String fileName = this.uploadService.handleSaveUploadFile(file, "product");
        product.setImage(fileName);
        return "redirect:/admin/product";
    }
}
