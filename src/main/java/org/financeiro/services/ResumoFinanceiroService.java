package org.financeiro.services;

import org.financeiro.dtos.ResumoFinanceiroDto;
import org.financeiro.dtos.TransacaoDto;

import java.util.List;

public interface ResumoFinanceiroService {
    ResumoFinanceiroDto getResumoFinanceiro(List<TransacaoDto> transacaoDtoList);
}
