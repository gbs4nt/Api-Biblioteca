package com.biblioteca.digital.model;

public record CriarLivroDTO(String titulo, Double preco,Usuario usuario, String nomeAutor, String genero, boolean alugado) {
}
