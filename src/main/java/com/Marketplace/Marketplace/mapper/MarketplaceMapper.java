package com.Marketplace.Marketplace.mapper;

import com.Marketplace.Marketplace.dto.*;
import com.Marketplace.Marketplace.entity.*;
import org.mapstruct.Mapper;
import java.util.List;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MarketplaceMapper {
    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(UsuarioDTO dto);
    List<UsuarioDTO> toUsuarioDTOList(List<Usuario> usuarios);

    CategoriaDTO toDTO(Categoria categoria);
    Categoria toEntity(CategoriaDTO dto);
    List<CategoriaDTO> toCategoriaDTOList(List<Categoria> categorias);

    ProdutoDTO toDTO(Produto produto);
    Produto toEntity(ProdutoDTO dto);
    List<ProdutoDTO> toProdutoDTOList(List<Produto> produtos);

    CarrinhoItemDTO toDTO(CarrinhoItem item);
    CarrinhoItem toEntity(CarrinhoItemDTO dto);
    List<CarrinhoItemDTO> toCarrinhoItemDTOList(List<CarrinhoItem> itens);

    CarrinhoDTO toDTO(Carrinho carrinho);
    Carrinho toEntity(CarrinhoDTO dto);
    List<CarrinhoDTO> toCarrinhoDTOList(List<Carrinho> carrinhos);

    PedidoItemDTO toDTO(PedidoItem item);
    PedidoItem toEntity(PedidoItemDTO dto);
    List<PedidoItemDTO> toPedidoItemDTOList(List<PedidoItem> itens);

    PedidoDTO toDTO(Pedido pedido);
    Pedido toEntity(PedidoDTO dto);
    List<PedidoDTO> toPedidoDTOList(List<Pedido> pedidos);

    PagamentoDTO toDTO(Pagamento pagamento);
    Pagamento toEntity(PagamentoDTO dto);
    List<PagamentoDTO> toPagamentoDTOList(List<Pagamento> pagamentos);

}
