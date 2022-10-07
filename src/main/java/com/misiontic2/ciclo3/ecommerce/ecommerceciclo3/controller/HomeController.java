package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {
    
    //Atributos
    @Autowired //Inyecta al contenedor una instancia de tipo ProductService
    private ProductService productService;//Obtener los productos

    //Métodos

    @GetMapping("")
    public String home(Model model){

        model.addAttribute("productos", productService.findAll());
        return "/user/home";
    }
}
