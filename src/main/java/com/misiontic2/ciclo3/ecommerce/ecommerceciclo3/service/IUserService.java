package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import java.util.List;
import java.util.Optional;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Usuario;

public interface IUserService {
    
    //Métodos
    List<Usuario> findAll();
    Optional<Usuario> findById(Integer id);
    Usuario save (Usuario user);
    Optional<Usuario> findByEmail(String email);
}
