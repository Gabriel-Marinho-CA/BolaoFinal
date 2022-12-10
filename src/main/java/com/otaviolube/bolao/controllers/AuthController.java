package com.otaviolube.bolao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String teste(Model model) {
        model.addAttribute("page", "dashboard");
        return "index";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        if (this.isAuthenticated) {
            model.addAttribute("page", "dashboard");
            return "index";
        } else {
            return "login";
        }
    }

    @GetMapping(value = "/register")
    public String register(UserModel userModel) {
        return "register";
    }


    @PostMapping(value = "/adduser")
    public String registerUser(@Validated UserModel user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/register";
        }
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login() {
        if (this.isAuthenticated) {
            return "index";
        } else {
            return "login";
        }
    }

    @PostMapping(value = "/trylogin")
    public String logar(@Validated UserModel user, UserModel password, Model model) {
        UserModel userLogin = this.userRepository.Login(user.getEmail(), user.getPassword());
        if (userLogin != null) {
            this.isAuthenticated = true;
            return "redirect:/teste";
        }
        model.addAttribute("error", "Usuário ou senha inválidos!");
        return "/login";
    }

    @GetMapping(value = "/forgot-password")
    public String forgotPassword() {
        if (this.isAuthenticated) {
            return "index";
        } else {
            return "forgot-password";
        }
    }

    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    // public String validLogin(@RequestParam String userName, @RequestParam String
    // password) {
    // UserModel user;

    // if(userName.equals(user.getNome())) {}
    // return "teste";
    // }

}
