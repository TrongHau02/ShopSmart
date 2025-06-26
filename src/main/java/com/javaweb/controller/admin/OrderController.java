package com.javaweb.controller.admin;

import com.javaweb.domain.Order;
import com.javaweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/admin/order")
    public String getHomePage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            }
        } catch (Exception e) {

        }
        Pageable pageable = PageRequest.of(page - 1, 2);
        Page<Order> orders = this.orderService.handleGetAllOrder(pageable);
        model.addAttribute("orders", orders.getContent());
        model.addAttribute("totalPages", orders.getTotalPages());
        model.addAttribute("currentPage", page);
        return "admin/order/home";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", this.orderService.fechById(id));
        model.addAttribute("orderId", id);
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", this.orderService.fechById(id));
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String updateOrder(@ModelAttribute("order") Order order) {
        Order currentOrder = this.orderService.fechById(order.getId());
        if (!currentOrder.getStatus().equals(order.getStatus())) {
            currentOrder.setStatus(order.getStatus());
            this.orderService.handleSaveUpdate(currentOrder);
        }
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("orderDelete", this.orderService.fechById(id));
        model.addAttribute("orderId", id);
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String deleteOrder(@ModelAttribute("orderDelete") Order order) {
        this.orderService.handleDeleteOrderById(order.getId());
        return "redirect:/admin/order";
    }
}
