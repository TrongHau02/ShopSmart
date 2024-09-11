package com.javaweb.controller.auth;

import com.javaweb.domain.User;
import com.javaweb.domain.dto.RegisterDTO;
import com.javaweb.service.RoleService;
import com.javaweb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("auth/register", "registerUser", new RegisterDTO());
    }

    @PostMapping("/register")
    public ModelAndView processRegister(@Valid @ModelAttribute("registerUser") RegisterDTO registerUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("auth/register");
        }

        User user = this.userService.registerDTOtoUser(registerUser);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRole(this.roleService.findRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return new ModelAndView("redirect:/login");
    }
}
