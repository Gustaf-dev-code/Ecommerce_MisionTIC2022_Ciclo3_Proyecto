package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.User;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
    
    //Atributos

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    //Métodos

    // /user/register
    @GetMapping("/register")
    public String create(){
        return "user/registro";
    }

    @PostMapping("/save")
    public String save(User user){
        logger.info("Usuario registro: {}", user);
        user.setRol("USER");
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/acceder")
    public String acceder(User user, HttpSession session){
        logger.info("Accesos: {}", user);
        Optional<User> usuario = userService.findByEmail(user.getEmail());
        //logger.info("Usuario de base de datos: {}", usuario.get());

        if(usuario.isPresent()){
            session.setAttribute("idusuario", usuario.get().getId());
            if(usuario.get().getRol().equals("ADMIN")){
                return "redirect:/admin";
            }else{
                return "redirect:/";
            }
        }else{
            logger.info("Usuario no existente");
        }

        return "redirect:/";
    }

    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session){
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "user/compras";
    }
}
