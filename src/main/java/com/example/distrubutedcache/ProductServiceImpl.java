package com.example.distrubutedcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    @CachePut(value = "products", key = "#product.id")
    public Product saveProduct(Product product) {
        productRepository.save(product);
        System.out.println("Adding Product: "+ product);
        return product;
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void removeProduct(Integer id) {
        productRepository.deleteById(id);
        System.out.println("Removed Product with id: {}"+ id);
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public Product getProduct(Integer id) {
        System.out.println("Getting Product with id from Repository"+ id);
        Product product = productRepository.findById(id).orElse(null);
        System.out.println("Product retrieved from Repository: "+ product);
        return product;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
