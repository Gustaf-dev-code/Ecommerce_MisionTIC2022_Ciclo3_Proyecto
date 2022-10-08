package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.User;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.repositoryDAO.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

    //Atributos
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<User> findById(Integer id) {
        
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    
    
}
