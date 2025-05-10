package org.example.projectbcms.dtos;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Integer quantityInStock;
    private BigDecimal buyPrice;
    private Long productLineId;

}
