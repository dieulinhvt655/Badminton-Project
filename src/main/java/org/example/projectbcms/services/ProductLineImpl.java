package org.example.projectbcms.services;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.models.ProductLine;
import org.example.projectbcms.repositories.ProductLineRepository;
import org.example.projectbcms.services.serviceInterfaces.ProductLineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if(!Objects.isNull(productLine.getName())) {
            productLinePresent.setName(productLine.getName());
        }
        if(!Objects.isNull(productLine.getDescription())) {
            productLinePresent.setDescription(productLine.getDescription());
        }
        if (!Objects.isNull(productLine.getImage())){
            productLinePresent.setImage(productLine.getImage());
        }

        return productLineRepository.save(productLinePresent);
    }

    @Override
    public ProductLine getProductLineById(Long id) {
        ProductLine productLine = productLineRepository.findById(id)
                .orElseThrow();

        return productLine;
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
