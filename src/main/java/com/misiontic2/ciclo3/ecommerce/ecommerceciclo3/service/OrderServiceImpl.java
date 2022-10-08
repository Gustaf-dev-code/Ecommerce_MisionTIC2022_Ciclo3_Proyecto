package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Order;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.repositoryDAO.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService{

    //Atributos
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
    
}
