package com.otaviolube.bolao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.otaviolube.bolao.models.SelecaoModel;
import com.otaviolube.bolao.models.SelecaoRepository;

@Controller
public class SelecaoController {

    @Autowired
    SelecaoRepository repo;

    @GetMapping(value = "/selecoes")
    public String getSelecoes(Model model){

        model.addAttribute("page", "selecoes");
        model.addAttribute("selecoes", repo.findAll());

        return "index";
    }

    @PostMapping(value = "/addselecao")
    public String addSelecao(SelecaoModel selecao, Model model, BindingResult result){

        if(result.hasErrors()){
            return "/selecoes";
        }

        repo.save(selecao);

        return "/selecoes";

    }

}
