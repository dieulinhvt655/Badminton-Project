package org.example.projectbcms.service;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.model.Product;
import org.example.projectbcms.repository.ProductRepository;
import org.example.projectbcms.service.serviceInterface.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // dependency injection
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(()-> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product updateProduct) {
        Product productPresent = getProductById(id);

        productPresent.setName(updateProduct.getName());
        productPresent.setQuantityInStock(updateProduct.getQuantityInStock());
        productPresent.setDescription(updateProduct.getDescription());
        productPresent.setBuyPrice(updateProduct.getBuyPrice());

        return productRepository.save(productPresent);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        System.out.println("Product deleted successfully");
    }
}
