package com.api_vendinha.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos") // Especifica o nome da tabela no banco de dados que será associada a esta
                          // entidade.
@NoArgsConstructor // Gera um construtor sem argumentos, necessário para a criação de instâncias da
                   // entidade pelo JPA.
@AllArgsConstructor
// Gera um construtor que aceita argumentos para todos os campos, útil para
// criar instâncias com todos os dados.
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o valor do ID será gerado automaticamente pelo
                                                        // banco de dados (auto-incremento).
    private Long id;

    @Column(nullable = false) // Especifica que a coluna no banco de dados não pode ser nula.
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Long userId;

}
