package org.financeiro.dtos;

import org.financeiro.models.Categoria;

import java.time.LocalDate;

public record FiltroDto(LocalDate dataInicio, LocalDate dataFim, CategoriaDto categoria, String classificacao) {
}
