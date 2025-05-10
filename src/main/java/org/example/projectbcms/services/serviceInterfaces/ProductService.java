package org.example.projectbcms.services.serviceInterfaces;

import org.example.projectbcms.dtos.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ProductDTO createProduct(ProductDTO product);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO updateProduct(Long id, ProductDTO updateProduct);

    void deleteProduct(Long id);
}
