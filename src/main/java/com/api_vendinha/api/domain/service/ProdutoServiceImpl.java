package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;

import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.entities.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoServiceInterface {
    private final ProdutoRepository produtoRepository;
    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public ProdutoResponseDto save(ProdutoRequestDto produtoRequestDto) {
        Produto produto = new Produto();
        // Define o nome do usuário a partir do DTO.
        produto.setName(produtoRequestDto.getName());
        produto.setQuantity(produtoRequestDto.getQuantity());
        produto.setPrice(produtoRequestDto.getPrice());
        produto.setUserId(produtoRequestDto.getUserId());

        Produto produtoSaved = produtoRepository.save(produto);

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();

        produtoResponseDto.setId(produtoSaved.getId());
        produtoResponseDto.setPrice(produtoSaved.getPrice());
        produtoResponseDto.setName(produtoSaved.getName());
        produtoResponseDto.setQuantity(produtoSaved.getQuantity());


        return produtoResponseDto;
    }

    @Override
    public ProdutoResponseDto atualizar(ProdutoRequestDto produtoRequestDto, Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setName(produtoRequestDto.getName());
        produto.setQuantity(produtoRequestDto.getQuantity());
        produto.setPrice(produtoRequestDto.getPrice());


        Produto produtoSaved = produtoRepository.save(produto);

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();
        produtoResponseDto.setId(produtoSaved.getId());
        produtoResponseDto.setPrice(produtoSaved.getPrice());
        produtoResponseDto.setName(produtoSaved.getName());
        produtoResponseDto.setQuantity(produtoSaved.getQuantity());

        return produtoResponseDto;
    }

    @Override
    public ProdutoResponseDto buscar(Long id) {

            Produto produto = produtoRepository.findById(id).orElseThrow();

            ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto();
            produtoResponseDto.setId(produto.getId());
            produtoResponseDto.setQuantity(produto.getQuantity());
            produtoResponseDto.setName(produto.getName());
            produtoResponseDto.setPrice(produto.getPrice());
            produtoResponseDto.setUserId(produto.getUserId());

            return produtoResponseDto;
    }

    @Override
    public List<Produto> listar() {


        return  produtoRepository.findAll();
    }

}





