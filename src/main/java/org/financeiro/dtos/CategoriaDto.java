package org.financeiro.dtos;

public record CategoriaDto(int id,
                           String nome,
                           String descricao,
                           UsuarioDto usuarioDto) {
}
