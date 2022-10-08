package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import java.util.Optional;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.User;

public interface IUserService {
    
    //Métodos
    Optional<User> findById(Integer id);
    User save (User user);
}
