package com.javaweb.controller.auth;

import com.javaweb.dto.RegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public ModelAndView showRegisterPage(){
        return new ModelAndView("auth/register", "registerUser", new RegisterDTO());
    }

    @PostMapping("/register")
    public ModelAndView processRegister(@ModelAttribute("registerUser") RegisterDTO registerUser){
        return new ModelAndView("auth/register");
    }
}
