package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Order;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.User;
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

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }


    public String generarNumeroOrden(){
        int numero = 0;
        String numeroConcatenado = "";

        List<Order> orders = findAll();

        List<Integer> numbers = new ArrayList<Integer>();

        orders.stream().forEach(ordenes -> numbers.add(Integer.parseInt(ordenes.getNumero())));

        if(orders.isEmpty()){
            numero = 1;
        }else{
            numero = numbers.stream().max(Integer::compare).get();
            numero++;
        }

        if(numero<10){
            numeroConcatenado = "000000000" + String.valueOf(numero);
        }else if(numero < 100){
            numeroConcatenado = "00000000" + String.valueOf(numero);
        }else if(numero < 1000){
            numeroConcatenado = "0000000" + String.valueOf(numero);
        }else if(numero < 10000){
            numeroConcatenado = "000000" + String.valueOf(numero);
        }

        return numeroConcatenado;
    }

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }
    
}
