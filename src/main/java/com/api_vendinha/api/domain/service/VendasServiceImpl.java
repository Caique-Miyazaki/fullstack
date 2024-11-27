package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.Infrastructure.repository.VendasRepository;
import com.api_vendinha.api.domain.dtos.request.VendasRequestDto;
import com.api_vendinha.api.domain.dtos.response.VendasResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.Vendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendasServiceImpl implements VendasServiceInterface {
    private final VendasRepository vendasRepository;
    private final ProdutoRepository produtoRepository;
    private final UserRepository userRepository;

    @Autowired
    public VendasServiceImpl(VendasRepository vendasRepository, ProdutoRepository produtoRepository, UserRepository userRepository) {
        this.vendasRepository = vendasRepository;
        this.produtoRepository = produtoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public VendasResponseDto realizarVenda(VendasRequestDto vendaRequestDto) {
        Produto produto = produtoRepository.findById(vendaRequestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantity() < vendaRequestDto.getQuantity()) {
            throw new RuntimeException("Quantidade em estoque insuficiente para a venda");
        }

        produto.setQuantity(produto.getQuantity() - vendaRequestDto.getQuantity());
        produtoRepository.save(produto);

        Vendas novaVenda = new Vendas();
        novaVenda.setUserId(vendaRequestDto.getUserId());
        novaVenda.setProductId(produto.getId());
        novaVenda.setQuantity(vendaRequestDto.getQuantity());
        novaVenda.setPrice(vendaRequestDto.getPrice());
        novaVenda.setActive(true); // Venda começa como ativa por padrão

        Vendas vendaSalva = vendasRepository.save(novaVenda);

        return new VendasResponseDto(
                vendaSalva.getId(),
                vendaSalva.getUserId(),
                vendaSalva.getProductId(),
                vendaSalva.getQuantity(),
                vendaSalva.getPrice()
        );
    }

    @Override
    public List<VendasResponseDto> listarVendas() {
        List<Vendas> vendasList = vendasRepository.findByActiveTrue(); // Apenas vendas ativas
        return vendasList.stream()
                .map(vendaItem -> new VendasResponseDto(
                        vendaItem.getId(),
                        vendaItem.getUserId(),
                        vendaItem.getProductId(),
                        vendaItem.getQuantity(),
                        vendaItem.getPrice()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public VendasResponseDto buscarVenda(Long id) {
        Vendas vendaEncontrada = vendasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        return new VendasResponseDto(
                vendaEncontrada.getId(),
                vendaEncontrada.getUserId(),
                vendaEncontrada.getProductId(),
                vendaEncontrada.getQuantity(),
                vendaEncontrada.getPrice()
        );
    }

    @Override
    public int inativarVenda(Long id) {
        Vendas venda = vendasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        if (!venda.isActive()) {
            throw new RuntimeException("Venda já está inativa");
        }

        venda.setActive(false);
        vendasRepository.save(venda);

        // Retorna a quantidade de produto subtraída
        return venda.getQuantity();
    }
}
