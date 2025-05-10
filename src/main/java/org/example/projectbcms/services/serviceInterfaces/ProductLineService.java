package org.example.projectbcms.services.serviceInterfaces;

import org.example.projectbcms.models.ProductLine;

import java.util.List;

public interface ProductLineService {

    ProductLine createProductLine(ProductLine productLine);

    ProductLine updateProductLine(Long id, ProductLine productLine);

    ProductLine getProductLineById(Long id);

    List<ProductLine> getAllProductLines();

    void deleteProductLineById(Long id);


}
