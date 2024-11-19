package com.api_vendinha.api.domain.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
@NoArgsConstructor
public class ProdutoRequestDto {
    private Long id;
    private String name;
    private int quantity;
    private float price;
    private Long userId;

}

