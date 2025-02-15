package org.example.projectbcms.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.model.ProductLine;
import org.example.projectbcms.service.serviceInterface.ProductLineService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-lines")
@RequiredArgsConstructor
public class ProductLineController {

    private final ProductLineService productLineService;

    @PostMapping("/them-dong-san-pham-moi")
    public ProductLine createProductLine(@RequestBody ProductLine newProductLine) {
        return productLineService.createProductLine(newProductLine);
    }

    @GetMapping("/hien-thi-thong-tin-dong-san-pham/{id}")
    public ProductLine getProductLineById(@PathVariable Long id) {
        return productLineService.getProductLineById(id);
    }


}
