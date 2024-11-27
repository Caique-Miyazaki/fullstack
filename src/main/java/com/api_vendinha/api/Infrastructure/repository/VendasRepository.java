package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendasRepository extends JpaRepository<Vendas, Long> {
    List<Vendas> findByActiveTrue(); // Busca apenas vendas ativas
}
