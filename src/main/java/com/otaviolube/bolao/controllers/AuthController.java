package com.otaviolube.bolao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.otaviolube.bolao.models.UserModel;
import com.otaviolube.bolao.models.UserRepository;

@Controller
public class AuthController {
    
    private Boolean isAuthenticated;

    @Autowired
    UserRepository userRepository;

    public AuthController() {
        this.isAuthenticated = false;
    }

    @GetMapping(value = "/teste")
    public String teste(Model model){
        model.addAttribute("page", "dashboard");
        return "index";
    }

    @GetMapping(value = "/")
    public String index(Model model){
        if(this.isAuthenticated){
            model.addAttribute("page", "dashboard");
            return "index";
        }else{
            return "login";
        }
    }

    @GetMapping(value = "/register")
    public String register(UserModel userModel){
        return "register";
    }

    @PostMapping(value = "/adduser")
    public String registerUser(@Validated UserModel user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/register";
        }
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login(){
        if(this.isAuthenticated){
            return "index";
        }else{
            return "login";
        }
    }

    @GetMapping(value = "/forgot-password")
    public String forgotPassword(){
        if(this.isAuthenticated){
            return "index";
        }else{
            return "forgot-password";
        }
    }
}
