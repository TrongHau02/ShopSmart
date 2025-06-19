package com.javaweb.controller.admin;

import com.javaweb.repository.OrderRepository;
import com.javaweb.service.OrderService;
import com.javaweb.service.ProductService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping(value = {"/admin"})
    public String getHomePage(Model model) {
        model.addAttribute("countUser", this.userService.handleCountUser());
        model.addAttribute("countProduct", this.productService.handleCountProduct());
        model.addAttribute("countOrder", this.orderService.handleCountOrder());
        return "admin/dashboard/home";
    }
}
