package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.VendasRequestDto;
import com.api_vendinha.api.domain.dtos.response.VendasResponseDto;
import com.api_vendinha.api.domain.entities.Vendas;

import com.api_vendinha.api.domain.service.VendasServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin(origins = "*")
public class VendasController {

    private final VendasServiceImpl vendasService;

    @Autowired

    public VendasController(VendasServiceImpl vendasService) {
        this.vendasService = vendasService;
    }

    @PostMapping()
        public VendasResponseDto realizarVenda(@RequestBody VendasRequestDto vendaRequestDto) {
            return vendasService.realizarVenda(vendaRequestDto);
        }
    @GetMapping
    public List<VendasResponseDto> listarVendas() {
        return vendasService.listarVendas();
    }

    // Rota para buscar uma venda específica
    @GetMapping("/{id}")
    public VendasResponseDto buscarVenda(@PathVariable Long id) {
        return vendasService.buscarVenda(id);
    }
    }



