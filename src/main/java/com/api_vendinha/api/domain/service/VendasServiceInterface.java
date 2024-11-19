package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.VendasRequestDto;
import com.api_vendinha.api.domain.dtos.response.VendasResponseDto;

import java.util.List;

public interface VendasServiceInterface {
    VendasResponseDto realizarVenda(VendasRequestDto vendaRequestDto);
    List<VendasResponseDto> listarVendas(); // Se você quiser listar todas as vendas
    VendasResponseDto buscarVenda(Long id); // Para buscar uma venda específica

}
