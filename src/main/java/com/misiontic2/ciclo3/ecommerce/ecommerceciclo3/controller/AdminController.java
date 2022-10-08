package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Order;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Product;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.IOrderService;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.IUserService;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    //Atributos

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

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

    @GetMapping("/ordersDetails/{id}")
    public String detalleOrden(Model model, @PathVariable Integer id){
        logger.info("Id de la orden: {}", id);
        Order order = ordersService.findById(id).get();

        model.addAttribute("detalles", order.getDetail());
        return "admin/detalleorden";
    }
}
