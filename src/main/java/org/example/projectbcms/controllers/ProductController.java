package org.example.projectbcms.controllers;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.dtos.ProductDTO;
import org.example.projectbcms.services.serviceInterfaces.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/tao-san-pham")
    public ProductDTO addProduct(@RequestBody ProductDTO newProduct) {
        return productService.createProduct(newProduct);
    }

    @GetMapping("/hien-thi-thong-tin-san-pham/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/hien-thi-tat-ca-san-pham")
    public List<ProductDTO> displayAllProduct(){
        return productService.getAllProducts();
    }

    @PutMapping("/cap-nhat-san-pham/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO newProduct) {
        return productService.updateProduct(id, newProduct);
    }

    @DeleteMapping("/xoa-san-pham/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

}
