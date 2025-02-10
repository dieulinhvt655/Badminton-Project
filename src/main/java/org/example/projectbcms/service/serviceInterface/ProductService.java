package org.example.projectbcms.service.serviceInterface;

import org.example.projectbcms.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product createProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product updateProduct);

    void deleteProduct(Long id);
}
