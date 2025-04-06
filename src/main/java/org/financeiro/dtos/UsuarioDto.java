package org.financeiro.dtos;

public record UsuarioDto(int id,
                         String nome,
                         String email,
                         String senha) {
}
