package com.biblioteca.digital.model;

import java.util.Set;

public record CriarUsuarioDTO(String nome,String cpf, Set<Livro> livrosAlugados) {
}
