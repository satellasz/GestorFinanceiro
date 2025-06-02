package org.financeiro.dtos;

public record UsuarioDto(long id,
                         String nome,
                         String email,
                         String senha) {
}
