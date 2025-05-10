package org.example.projectbcms.services;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.dtos.ProductDTO;
import org.example.projectbcms.models.Product;
import org.example.projectbcms.models.ProductLine;
import org.example.projectbcms.repositories.ProductLineRepository;
import org.example.projectbcms.repositories.ProductRepository;
import org.example.projectbcms.services.serviceInterfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // dependency injection
    private final ProductRepository productRepository;
    private final ProductLineRepository productLineRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductLine productLine = productLineRepository.findById(productDTO.getProductLineId())
                .orElseThrow();

        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setQuantityInStock(productDTO.getQuantityInStock());
        newProduct.setProductLine(productLine);
        newProduct.setBuyPrice(productDTO.getBuyPrice());

        productRepository.save(newProduct);

        productDTO.setId(newProduct.getId());

        return productDTO;
    }

//    @Override
//    public ProductDTO getProductById(Long id) {
//        Optional<Product> product = productRepository.findById(id);
//        return product.orElseThrow(()-> new RuntimeException("Product not found"));
//    }
    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow();
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .quantityInStock(product.getQuantityInStock())
                .buyPrice(product.getBuyPrice())
                .productLineId(product.getProductLine().getId())
                .build();
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setBuyPrice(product.getBuyPrice());
        dto.setDescription(product.getDescription());
        dto.setQuantityInStock(product.getQuantityInStock());
//        dto.setProductLineId(product.getProductLineId());
        // thêm các field khác nếu có
        return dto;
    }

//    @Override
//    public List<ProductDTO> getAllProducts() {
//        List<ProductDTO> products = productRepository.findAll();
//        return products;
//    }
    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProductDTO updateProduct(Long id, ProductDTO updateProduct) {
        Product productPresent = productRepository.findById(id)
                .orElseThrow();
        ProductLine productLine = productLineRepository.findById(updateProduct.getProductLineId())
                .orElseThrow();

        productPresent.setName(updateProduct.getName());
        productPresent.setProductLine(productLine);
        productPresent.setDescription(updateProduct.getDescription());
        productPresent.setQuantityInStock(updateProduct.getQuantityInStock());
        productPresent.setBuyPrice(updateProduct.getBuyPrice());

        productRepository.save(productPresent);

        return ProductDTO.builder()
                .id(productPresent.getId())
                .name(productPresent.getName())
                .productLineId(productLine.getId())
                .description(productPresent.getDescription())
                .quantityInStock(productPresent.getQuantityInStock())
                .buyPrice(productPresent.getBuyPrice())
                .build();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        System.out.println("Product deleted successfully");
    }
}
