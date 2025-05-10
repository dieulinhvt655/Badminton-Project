package org.example.projectbcms.controllers;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.models.ProductLine;
import org.example.projectbcms.services.serviceInterfaces.ProductLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/cap-nhat/{id}")
    public ProductLine updateProductLine(@PathVariable Long id, @RequestBody ProductLine updateProductLine) {
        return productLineService.updateProductLine(id, updateProductLine);
    }

    //????????????????????????????????????????
    @DeleteMapping("xoa-dong-san-pham/{id}")
    public ResponseEntity<?> deleteProductLineById(@PathVariable Long id) {
        productLineService.deleteProductLineById(id);
        return ResponseEntity.ok().body("Product line with id " + id + " was deleted");
    }

    @GetMapping("/hien-thi-danh-sach-dong-san-pham")
    public List <ProductLine> getAllProductLines() {
        return productLineService.getAllProductLines();
    }


}
