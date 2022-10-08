package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.OrderDetail;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.repositoryDAO.IOrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    //Atributos
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
    
}
