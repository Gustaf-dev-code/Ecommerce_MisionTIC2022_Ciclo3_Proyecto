package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import java.util.List;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Order;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.User;

public interface IOrderService {
    
    //Métodos
    List<Order> findAll();
    Order save (Order order);
    String generarNumeroOrden();
    List<Order> findByUser(User user);
}
