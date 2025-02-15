package org.example.projectbcms.service;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.model.ProductLine;
import org.example.projectbcms.repository.ProductLineRepository;
import org.example.projectbcms.service.serviceInterface.ProductLineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//
@RequiredArgsConstructor
public class ProductLineImpl implements ProductLineService {

    //dependency injection
    private final ProductLineRepository productLineRepository;

    @Override
    public ProductLine createProductLine(ProductLine productLine) {
        return productLineRepository.save(productLine);
    }

    @Override
    public ProductLine updateProductLine(Long id, ProductLine productLine) {
        ProductLine productLinePresent = getProductLineById(id);
        // chỉ update đc description và img
        productLinePresent.setName(productLine.getName());
        productLinePresent.setDescription(productLine.getDescription());
        productLinePresent.setImage(productLine.getImage());

        return productLineRepository.save(productLinePresent);
    }

    @Override
    public ProductLine getProductLineById(Long id) {
        Optional<ProductLine> productLine = productLineRepository.findById(id);
        return productLine.orElseThrow(()-> new RuntimeException("Product Line Not Found"));
    }

    @Override
    public List<ProductLine> getAllProductLines() {
        List<ProductLine> productLines = productLineRepository.findAll();
        return productLines;
    }

    @Override
    public void deleteProductLineById(Long id) {
        productLineRepository.deleteById(id);
        System.out.println("Product Line Deleted Successfully");
    }

}
