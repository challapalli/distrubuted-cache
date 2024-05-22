package com.example.distrubutedcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    Environment environment;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        String port = environment.getProperty("local.server.port");
        product.setSource("Application@" + port);
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok().body(savedProduct);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            System.out.println("Product: "+ product);
            return ResponseEntity.ok().body(product);
        } else {
            System.out.println("Product with id not found"+ id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable("id") Integer id) {
        productService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("all")
    public List<Product> getAll() {
        return productService.getAll();
    }

}
