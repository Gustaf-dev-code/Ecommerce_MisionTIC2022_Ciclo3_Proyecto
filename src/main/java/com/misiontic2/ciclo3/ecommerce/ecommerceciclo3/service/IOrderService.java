package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import java.util.List;
import java.util.Optional;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Order;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Usuario;

public interface IOrderService {
    
    //Métodos
    List<Order> findAll();
    Optional<Order> findById(Integer id);
    Order save (Order order);
    String generarNumeroOrden();
    List<Order> findByUser(Usuario user);
}
