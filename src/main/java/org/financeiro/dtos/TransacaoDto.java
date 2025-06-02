package org.financeiro.dtos;


import org.financeiro.enums.ClassificacaoTransacao;

import java.time.LocalDate;

public record TransacaoDto(long id,
                           String descricao,
                           double valor,
                           CategoriaDto categoriaDto,
                           ClassificacaoTransacao classificacao,
                           UsuarioDto usuarioDto,
                           LocalDate dataTransacao) {
}
