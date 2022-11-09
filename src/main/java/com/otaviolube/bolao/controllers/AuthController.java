package com.otaviolube.bolao.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    
    private Boolean isAuthenticated;

    public AuthController() {
        this.isAuthenticated = false;
    }

    @GetMapping(value = "/")
    public String index(){
        if(this.isAuthenticated){
            return "index";
        }else{
            return "login";
        }
    }

    @GetMapping(value = "/register")
    public String register(){
        if(this.isAuthenticated){
            return "index";
        }else{
            return "register";
        }
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
