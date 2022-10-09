package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.repositoryDAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Usuario;

@Repository
public interface IUserRepository extends JpaRepository<Usuario, Integer>{

    Optional<Usuario> findByEmail(String email);
    
}
