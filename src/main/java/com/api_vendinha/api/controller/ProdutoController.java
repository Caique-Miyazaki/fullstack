package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.ProdutoRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutoResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.service.ProdutoServiceInterface;
import com.api_vendinha.api.domain.service.VendasServiceImpl; // Importação do VendaService
import com.api_vendinha.api.domain.service.VendasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    private final ProdutoServiceInterface produtoService;
     // Declaração do VendaService

    @Autowired
    public ProdutoController(ProdutoServiceInterface produtoService, VendasServiceImpl vendaService) {
        this.produtoService = produtoService;

    }

    @PostMapping
    public ProdutoResponseDto save(@RequestBody ProdutoRequestDto produtoRequestDto) {
        return produtoService.save(produtoRequestDto);
    }

    @PutMapping("/produto/{id}")
    public ProdutoResponseDto atualizar(@RequestBody ProdutoRequestDto produtoRequestDto, @PathVariable Long id) {
        return produtoService.atualizar(produtoRequestDto, id);
    }

    @GetMapping("/produto/{id}")
    public ProdutoResponseDto buscar(@PathVariable Long id) {
        return produtoService.buscar(id);
    }

    @GetMapping("/produtos")
    public List<Produto> listar() {
        return produtoService.listar();
    }


}
