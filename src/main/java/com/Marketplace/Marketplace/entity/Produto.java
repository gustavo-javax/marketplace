package com.Marketplace.Marketplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")



public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome deve ser declarado")
    @Column(nullable = false)
    private String nome;

    private String descricao;

    @NotNull(message = "O preço deve ser declarado")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0")
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull(message = "O estoque deve ser declarado")
    @Min(value = 0, message = "O estoque deve ser maior ou igual a 0")
    @Column(nullable = false)
    private Integer estoque;

    @NotNull(message = "A categoria deve ser declarada")
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;




}
