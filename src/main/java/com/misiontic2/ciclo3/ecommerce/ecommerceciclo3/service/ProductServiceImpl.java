package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Product;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.repositoryDAO.IProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    //Atributos
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product save(Product product) {   
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> get(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
}
