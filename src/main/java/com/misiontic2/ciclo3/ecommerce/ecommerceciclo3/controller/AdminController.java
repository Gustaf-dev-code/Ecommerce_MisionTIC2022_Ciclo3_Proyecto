package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Product;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.IOrderService;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.IUserService;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    //Atributos
    @Autowired
    private ProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService ordersService;

    @GetMapping("")
    public String home(Model model) {

        List<Product> productos = productService.findAll();
        model.addAttribute("productos", productos);
        return "admin/home";
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("usuarios", userService.findAll());

        return "admin/usuarios";
    }

    @GetMapping("/orders")
    public String orders(Model model){
        model.addAttribute("ordenes", ordersService.findAll());
        return "admin/ordenes";
    }
}
