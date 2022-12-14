package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

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
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Usuario;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.IOrderDetailService;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.IOrderService;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.IUserService;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {
    
    //Atributos
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired //Inyecta al contenedor una instancia de tipo ProductService
    private ProductService productService;//Obtener los productos

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;
    
    //Almacena los detalles de la orden
    List<OrderDetail> details = new ArrayList<OrderDetail>();
    
    //Almacena los datos de la ordén
    Order order = new Order();

    //Métodos

    @GetMapping("")
    public String home(Model model, HttpSession session){
        log.info("Sesión del usuario: {}", session.getAttribute("idusuario"));
        model.addAttribute("productos", productService.findAll());

        //Sesión
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        return "user/home";
    }

    @GetMapping("productHome/{id}")
    public String productHome(@PathVariable Integer id, Model model, HttpSession session){
        log.info("Id producto enviado como parámetro {}", id);
        Product prod = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        prod = optionalProduct.get();
        model.addAttribute("producto", prod);
        //Sesión
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "user/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model, HttpSession session){
        OrderDetail orderDetail = new OrderDetail();
        Product product = new Product();
        double sumaTotal = 0;
        //Sesión
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        Optional<Product> optionalProduct = productService.get(id);
        log.info("Producto añadido: {}", optionalProduct.get());
        log.info("Cantidad: {}", cantidad);
        product = optionalProduct.get();
        orderDetail.setCantidad(cantidad);
        orderDetail.setPrecio(product.getPrecio());
        orderDetail.setNombre(product.getNombre());
        orderDetail.setTotal(product.getPrecio()*cantidad);
        orderDetail.setProduct(product);

        //Validad que el producto no se añada dos veces
        Integer idProduct = product.getId();
        boolean ingresado = details.stream().anyMatch(prod -> prod.getProduct().getId() == idProduct);

        if (!ingresado) {
            details.add(orderDetail);
        }

          

        sumaTotal = details.stream().mapToDouble(dt->dt.getTotal()).sum();
        order.setTotal(sumaTotal);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        return "user/carrito";
    }

    //Eliminar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteCart(@PathVariable Integer id, Model model){

        //Lista nueva de productos
        List<OrderDetail> newOrders = new ArrayList<OrderDetail>();

        for(OrderDetail orderDetail: details){
            if(orderDetail.getProduct().getId()!=id){
                newOrders.add(orderDetail);
            }
        }

        //Retorno de la nueva lista con los productos restantes
        details = newOrders;

        double sumaTotal = 0;

        sumaTotal = details.stream().mapToDouble(dt->dt.getTotal()).sum();
        order.setTotal(sumaTotal);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        return "user/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session){
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        //Sesión
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        return "/user/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session){

        Usuario user = userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        return "user/resumenorden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        Date fechaCreacion = new Date();
        order.setFechaCreacion(fechaCreacion);
        order.setNumero(orderService.generarNumeroOrden());

        //Obtener usuario
        Usuario user = userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        order.setUser(user);
        orderService.save(order);

        //Guardar detalles
        for(OrderDetail dt:details){
            dt.setOrder(order);
            orderDetailService.save(dt);
        }

        //Limpiar valores de la lista y orden
        order = new Order();
        details.clear();


        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombreBusqueda, Model model, HttpSession session){
        log.info("Nombre del producto: {}", nombreBusqueda);
        List<Product> products = productService.findAll().stream().filter(producto -> producto.getNombre().contains(nombreBusqueda)).collect(Collectors.toList());
        model.addAttribute("productos", products);
        //Sesión
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "user/home";
    }
}
