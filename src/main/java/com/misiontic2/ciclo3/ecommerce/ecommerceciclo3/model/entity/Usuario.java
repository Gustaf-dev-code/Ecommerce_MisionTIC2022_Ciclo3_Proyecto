package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Usuario {
    
    //Mapeo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Campo autocremental
    //Atributos
    private Integer id;
    private String nombre;
    private String username;
    private String email;
    private String direccion;
    private String telefono;
    private String rol;
    private String password;

    //-- Mapeo Entre usuario y producto
    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    //Constructor

    public Usuario(){
        
    }

    public Usuario(Integer id, String nombre, String username, String email, String direccion, String telefono,
            String rol, String password) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.password = password;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    

    //Métodos
    @Override
    public String toString() {
        return "User [id=" + id + ", nombre=" + nombre + ", username=" + username + ", email=" + email + ", direccion="
                + direccion + ", telefono=" + telefono + ", rol=" + rol + ", password=" + password + "]";
    }

   

    
    
}
