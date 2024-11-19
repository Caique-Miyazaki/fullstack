package com.api_vendinha.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade representando um usuário no sistema.
 *
 * Esta classe é mapeada para a tabela "users" no banco de dados e representa a estrutura dos dados
 * relacionados a um usuário.
 */
@Entity
@Table(name = "users") // Especifica o nome da tabela no banco de dados que será associada a esta entidade.
@NoArgsConstructor // Gera um construtor sem argumentos, necessário para a criação de instâncias da entidade pelo JPA.
@AllArgsConstructor // Gera um construtor que aceita argumentos para todos os campos, útil para criar instâncias com todos os dados.
@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
public class User {

    /**
     * Identificador único do usuário.
     *
     * Este campo é a chave primária da entidade e será gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o valor do ID será gerado automaticamente pelo banco de dados (auto-incremento).
    private Long id;

    /**
     * Nome do usuário.
     *
     * Este campo é obrigatório e não pode ser nulo, refletindo a necessidade de um nome para cada usuário.
     */
    @Column(nullable = false) // Especifica que a coluna no banco de dados não pode ser nula.
    private String name;
    private String email;
    private String password;
    private Boolean is_active;
    private String cpf_cnpj;
}
