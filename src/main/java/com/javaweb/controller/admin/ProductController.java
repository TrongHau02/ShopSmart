package com.javaweb.controller.admin;

import com.javaweb.domain.Product;
import com.javaweb.domain.Target;
import com.javaweb.domain.User;
import com.javaweb.service.FactoryService;
import com.javaweb.service.ProductService;
import com.javaweb.service.TargetService;
import com.javaweb.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private final UploadService uploadService;
    private final ProductService productService;
    private final FactoryService factoryService;
    private final TargetService targetService;

    public ProductController(UploadService uploadService, ProductService productService, FactoryService factoryService, TargetService targetService) {
        this.uploadService = uploadService;
        this.productService = productService;
        this.factoryService = factoryService;
        this.targetService = targetService;
    }

    @GetMapping(value = "/admin/product")
    public String getProductPage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                //convert from string to int
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page = 1
            }
        } catch (Exception e) {
            //page = 1
        }
        Pageable pageable = PageRequest.of(page - 1, 2);
        Page<Product> products = this.productService.findAllProduct(pageable);
        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        return "admin/product/home";
    }

    @GetMapping(value = {"/admin/product/create"})
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        model.addAttribute("targets", this.targetService.handlefindAllTarget());
        model.addAttribute("factorys", this.factoryService.handlefindAllFactory());
        return "admin/product/create";
    }

    @PostMapping(value = "/admin/product/create")
    public String createProduct(@Valid @ModelAttribute("newProduct") Product newProduct,
                                BindingResult newProductBindingRequest,
                                @RequestParam("imageFile") MultipartFile file, Model model) {
        String fileName = this.uploadService.handleSaveUploadFile(file, "product");
        newProduct.setImage(fileName);

        List<FieldError> errors = newProductBindingRequest.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>" + error.getField() + "-" + error.getDefaultMessage());
        }

        if (newProductBindingRequest.hasErrors()) {
            return "admin/product/create";
        }

        this.productService.handleSaveProduct(newProduct);

        return "redirect:/admin/product";
    }

    @GetMapping(value = {"/admin/product/{id}"})
    public String getDetailProductPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("productDetail", this.productService.findProductById(id));
        return "admin/product/detail";
    }

    @GetMapping(value = {"/admin/product/delete/{id}"})
    public String getDeleteProductPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("productDelete", new Product());
        return "admin/product/delete";
    }

    @PostMapping(value = "/admin/product/delete")
    public String handleDeleteProduct(@ModelAttribute("productDelete") Product product) {
        this.productService.handleDeleteProduct(product.getId());
        return "redirect:/admin/product";
    }

    @GetMapping(value = {"/admin/product/update/{id}"})
    public String getUpdateProductPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("productUpdate", this.productService.findProductById(id));
        model.addAttribute("targets", this.targetService.handlefindAllTarget());
        model.addAttribute("factorys", this.factoryService.handlefindAllFactory());
        return "admin/product/update";
    }

    @PostMapping(value = {"/admin/product/update"})
    public String handleUpdateProductById(@Valid @ModelAttribute("productUpdate") Product newProduct,
                                          BindingResult newProductBidingRequest,
                                          @RequestParam("imageProduct") MultipartFile file,
                                          Model model) {
        if (newProductBidingRequest.hasErrors()) {
            newProduct.setImage(this.productService.findProductById(newProduct.getId()).getImage());
            model.addAttribute("productUpdate", newProduct);
            model.addAttribute("targets", this.targetService.handlefindAllTarget());
            model.addAttribute("factorys", this.factoryService.handlefindAllFactory());
            return "admin/product/update";
        }

        Product currentProduct = this.productService.getById(newProduct.getId());
        if (currentProduct != null) {
            currentProduct.setName(newProduct.getName());
            currentProduct.setPrice(newProduct.getPrice());
            currentProduct.setShortDesc(newProduct.getShortDesc());
            currentProduct.setDetailDesc(newProduct.getDetailDesc());
            currentProduct.setTarget(newProduct.getTarget());
            String imageProduct = this.uploadService.handleSaveUploadFile(file, "product");
            if (!imageProduct.isEmpty()) {
                currentProduct.setImage(imageProduct);
            }
            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }
}
