package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.repositoryDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Order;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Usuario;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer>{
    
    //Métodos
    List<Order> findByUser(Usuario user);
}
