package org.example.projectbcms.dtos;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {

    private Long id;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private Long productId;

    private Long orderId;
}
