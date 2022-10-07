package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Order;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.OrderDetail;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Product;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {
    
    //Atributos
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired //Inyecta al contenedor una instancia de tipo ProductService
    private ProductService productService;//Obtener los productos
    
    //Almacena los detalles de la orden
    List<OrderDetail> details = new ArrayList<OrderDetail>();
    
    //Almacena los datos de la ordén
    Order order = new Order();

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

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){
        OrderDetail orderDetail = new OrderDetail();
        Product product = new Product();
        double sumaTotal = 0;

        Optional<Product> optionalProduct = productService.get(id);
        log.info("Producto añadido: {}", optionalProduct.get());
        log.info("Cantidad: {}", cantidad);
        product = optionalProduct.get();
        orderDetail.setCantidad(cantidad);
        orderDetail.setPrecio(product.getPrecio());
        orderDetail.setNombre(product.getNombre());
        orderDetail.setTotal(product.getPrecio()*cantidad);
        orderDetail.setProduct(product);

        details.add(orderDetail);

        sumaTotal = details.stream().mapToDouble(dt->dt.getTotal()).sum();
        order.setTotal(sumaTotal);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        return "user/carrito";
    }

}
