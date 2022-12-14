package com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.controller;


import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Product;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.model.entity.Usuario;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.IUserService;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.ProductService;
import com.misiontic2.ciclo3.ecommerce.ecommerceciclo3.service.UploadFileService;

@Controller
@RequestMapping("/products")
public class ProductController {

    //Atributos
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private UploadFileService upload;
    
    //Métodos
    @GetMapping("")
    public String show(Model model){//Model lleva información desde el backend a la vista
        model.addAttribute("products", productService.findAll());
        return "products/show";
    }

    @GetMapping("/create")
    public String create(){
        return "products/create";
    }

    @PostMapping("/save")
    public String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException{
        LOGGER.info("Este es el objeto producto {}",product);
        
        Usuario user = userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        product.setUser(user);

        //Imagen
        if(product.getId()==null){//Cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            product.setImagen(nombreImagen);
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {//PathVariable mapea el id que recibe el método y la pasa a la variable contigua de @PathVariable
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();

        LOGGER.info("Producto buscado: {}", product);
        model.addAttribute("producto",product);
        return "products/edit";
    }

    @PostMapping("/update")
    public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException{
        
        Product prod = new Product();
        prod = productService.get(product.getId()).get();
        
        if(file.isEmpty()){//Cuando editamos el producto sin actualizar la imagen
            
            product.setImagen(prod.getImagen());
        }else{//Cuando se actualiza la imagen
            //Eliminar cuando la imagen no esta por defecto
            if (!prod.getImagen().equals("defaut.jpg")) {
            upload.deleteImage(prod.getImagen());
        }
            String nombreImagen = upload.saveImage(file);
            product.setImagen(nombreImagen);
        }

        product.setUser(prod.getUser());
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        Product produ = new Product();
        produ = productService.get(id).get();

        //Eliminar cuando la imagen no esta por defecto
        if (produ.getImagen().equals("defaut.jpg")) {
            upload.deleteImage(produ.getImagen());
        }

        productService.delete(id);
        return "redirect:/products";
    }
}
