package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Usuario;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.repositoryDAO.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

    //Atributos
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<Usuario> findById(Integer id) {
        
        return userRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    
    
}
