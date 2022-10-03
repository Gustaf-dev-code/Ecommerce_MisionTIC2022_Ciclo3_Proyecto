package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import java.util.List;
import java.util.Optional;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Product;

public interface ProductService {
    
    public Product save(Product product);
    public Optional<Product> get(Integer id);
    public void update(Product product);
    public void delete(Integer id);
    public List<Product> findAll();
}
