package com.javaweb.controller;

import com.javaweb.domain.User;
import com.javaweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsers();
        System.out.println(arrUsers);
        model.addAttribute("eric", "test");
        return "hello";
    }

    @RequestMapping(value = "/admin/user")
    public String getUserPage(Model model) {
        List<User> arrUsers = this.userService.getAllUsers();
        model.addAttribute("users", arrUsers);
        return "admin/user/table-user";
    }

    @RequestMapping(value = "/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User newUser ) {
        this.userService.handleSaveUser(newUser);
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public String getUserById(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "admin/user/detail";
    }

    @GetMapping(value = "/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable("id") long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping(value = "/admin/user/update")
    public String updateUserPage(Model model, @ModelAttribute("newUser") User newUser) {
        User currentUser = this.userService.getUserById(newUser.getId());
        if (currentUser != null) {
            currentUser.setFullName(newUser.getFullName());
            currentUser.setPhone(newUser.getPhone());
            currentUser.setAddress(newUser.getAddress());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable("id") long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping(value = "/admin/user/delete")
    public String deleteUserPage(@ModelAttribute("newUser") User user) {
        this.userService.handleDeleteUser(user.getId());
        return "redirect:/admin/user";
    }
}
