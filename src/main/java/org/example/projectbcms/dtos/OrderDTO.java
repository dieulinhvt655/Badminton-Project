package org.example.projectbcms.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;

    private LocalDate orderDate;

    //    @JsonProperty("required_date")
    private LocalDate requiredDate;

    private LocalDate shippedDate;

    private String orderStatus;

    private String comments;

    //    @JsonProperty("user_id")
    private Long userId;
}
