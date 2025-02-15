package org.example.projectbcms.service.serviceInterface;

import org.example.projectbcms.model.ProductLine;

import java.util.List;

public interface ProductLineService {

    ProductLine createProductLine(ProductLine productLine);

    ProductLine updateProductLine(Long id, ProductLine productLine);

    ProductLine getProductLineById(Long id);

    List<ProductLine> getAllProductLines();

    void deleteProductLineById(Long id);


}
