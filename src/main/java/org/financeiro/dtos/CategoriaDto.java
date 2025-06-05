package org.financeiro.dtos;

public record CategoriaDto(long id,
                           String nome,
                           String descricao,
                           UsuarioDto usuarioDto) {
}
