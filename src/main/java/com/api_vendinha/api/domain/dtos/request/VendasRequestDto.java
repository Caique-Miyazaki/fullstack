package com.api_vendinha.api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VendasRequestDto {
    private Long userId;
    private Long productId;
    private int quantity;
    private float price;
}
