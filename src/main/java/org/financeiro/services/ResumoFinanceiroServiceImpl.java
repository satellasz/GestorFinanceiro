package org.financeiro.services;

import org.financeiro.dtos.ResumoFinanceiroDto;
import org.financeiro.dtos.TransacaoDto;
import org.financeiro.enums.ClassificacaoTransacao;

import java.util.List;

public class ResumoFinanceiroServiceImpl implements ResumoFinanceiroService {
    @Override
    public ResumoFinanceiroDto getResumoFinanceiro(List<TransacaoDto> transacaoDtoList) {
        double saldo = 0;
        double despesa = 0;
        double receita = 0;

        for (TransacaoDto transacaoDto : transacaoDtoList) {
            saldo += transacaoDto.valor();

            if (transacaoDto.classificacao() == ClassificacaoTransacao.DESPESA) {
                despesa += transacaoDto.valor();
            } else {
                receita += transacaoDto.valor();
            }
        }

        saldo = receita - despesa;

        return new ResumoFinanceiroDto(saldo, despesa, receita);
    }
}
