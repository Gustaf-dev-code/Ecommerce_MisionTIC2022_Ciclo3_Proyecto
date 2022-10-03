package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    //Atributos
    @GetMapping("")
    public String home() {
        return "admin/home";
    }
}
