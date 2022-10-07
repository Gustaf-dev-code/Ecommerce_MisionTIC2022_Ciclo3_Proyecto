package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Product;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {
    
    //Atributos
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired //Inyecta al contenedor una instancia de tipo ProductService
    private ProductService productService;//Obtener los productos

    //Métodos

    @GetMapping("")
    public String home(Model model){

        model.addAttribute("productos", productService.findAll());
        return "user/home";
    }

    @GetMapping("productHome/{id}")
    public String productHome(@PathVariable Integer id, Model model){
        log.info("Id producto enviado como parámetro {}", id);
        Product prod = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        prod = optionalProduct.get();
        model.addAttribute("producto", prod);

        return "user/productohome";
    }
}
