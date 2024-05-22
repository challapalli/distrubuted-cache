package com.example.distrubutedcache;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    void removeProduct(Integer id);

    Product getProduct(Integer id);

    List<Product> getAll();
}
