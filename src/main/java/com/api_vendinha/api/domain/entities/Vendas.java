package com.api_vendinha.api.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendas")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Vendas {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private int quantity;
        private float price;
        @Column(name = "product_id", nullable = false)
        private Long productId; // ID do produto vendido

        @Column(name = "user_id", nullable = false)
        private Long userId;

}
