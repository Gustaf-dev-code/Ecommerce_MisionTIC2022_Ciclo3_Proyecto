package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.repositoryDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.OrderDetail;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
    
}
