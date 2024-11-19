package com.api_vendinha.api.domain.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendasResponseDto {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private float price;
}
