CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    estoque INT NOT NULL,
    categoria_id BIGINT REFERENCES categoria(id)
);

CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT REFERENCES usuario(id),
    data_criacao TIMESTAMP,
    total DECIMAL(10,2),
    status VARCHAR(20)
);

CREATE TABLE pedido_item (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT REFERENCES pedido(id),
    produto_id BIGINT REFERENCES produto(id),
    quantidade INT,
    preco_unitario DECIMAL(10,2)
);

CREATE TABLE carrinho (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT UNIQUE REFERENCES usuario(id)
);

CREATE TABLE carrinho_item (
    id BIGSERIAL PRIMARY KEY,
    carrinho_id BIGINT REFERENCES carrinho(id),
    produto_id BIGINT REFERENCES produto(id),
    quantidade INT,
    preco_unitario DECIMAL(10,2)
);

CREATE TABLE pagamento (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT UNIQUE REFERENCES pedido(id),
    status VARCHAR(20),
    metodo VARCHAR(50),
    data_pagamento TIMESTAMP
);
