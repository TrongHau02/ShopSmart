package com.javaweb.controller.admin;

import com.javaweb.domain.User;
import com.javaweb.service.UploadService;
import com.javaweb.service.UserService;
import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;

    public UserController(UserService userService, UploadService uploadService) {
        this.userService = userService;
        this.uploadService = uploadService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsers();
        System.out.println(arrUsers);
        model.addAttribute("eric", "test");
        return "hello";
    }

    @GetMapping(value = "/admin/user")
    public String getUserPage(Model model) {
        List<User> arrUsers = this.userService.getAllUsers();
        model.addAttribute("users", arrUsers);
        return "admin/user/home";
    }

    @GetMapping(value = "/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User newUser, @RequestParam("avatarFile") MultipartFile file) {
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        //this.userService.handleSaveUser(newUser);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/admin/user/{id}")
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
