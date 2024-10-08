package com.javaweb.controller.admin;

import com.javaweb.domain.Role;
import com.javaweb.domain.User;
import com.javaweb.service.RoleService;
import com.javaweb.service.UploadService;
import com.javaweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin/user")
    public String getUserPage(Model model) {
        List<User> arrUsers = this.userService.getAllUsers();
        model.addAttribute("users", arrUsers);
        return "admin/user/home";
    }

    @GetMapping(value = "/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("roles", this.roleService.findAll());
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, @Valid @ModelAttribute("newUser") User newUser,
                                 BindingResult newUserBindingResult, @RequestParam("avatarFile") MultipartFile file) {
        /*List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>>>" + error.getField() + "-" + error.getDefaultMessage());
        }*/

        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(newUser.getPassword());
        newUser.setAvatar(avatar);
        newUser.setPassword(hashPassword);
        newUser.setRole(roleService.findRoleByName(newUser.getRole().getName()));
        this.userService.handleSaveUser(newUser);
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
        model.addAttribute("roles", this.roleService.findAll());
        return "admin/user/update";
    }

    @PostMapping(value = "/admin/user/update")
    public String updateUserPage(Model model, @Valid @ModelAttribute("newUser") User newUser,
                                 BindingResult newUserBindingResult,
                                 @RequestParam("avatarFile") MultipartFile file) {
        if (newUserBindingResult.hasErrors()) {
            model.addAttribute("newUser", newUser);
            model.addAttribute("roles", this.roleService.findAll());
            return "admin/user/update";
        }
        User currentUser = this.userService.getUserById(newUser.getId());
        if (currentUser != null) {
            currentUser.setFullName(newUser.getFullName());
            currentUser.setPhone(newUser.getPhone());
            currentUser.setAddress(newUser.getAddress());
            currentUser.setRole(roleService.findRoleByName(newUser.getRole().getName()));
            String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
            currentUser.setAvatar(avatar);
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
