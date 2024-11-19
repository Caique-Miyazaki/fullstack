package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    Optional<Produto> findByName(String name);
}
