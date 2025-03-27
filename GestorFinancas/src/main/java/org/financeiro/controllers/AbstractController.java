package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.enums.TipoCampoTexto;
import org.financeiro.exceptions.CampoInvalidoException;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.services.FormularioService;
import org.financeiro.services.FormularioServiceImpl;
import org.financeiro.services.PainelService;
import org.financeiro.services.PainelServiceImpl;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;
import org.financeiro.services.transacao.TransacaoService;
import org.financeiro.services.transacao.TransacaoServiceImpl;
import org.financeiro.utils.Utils;

public abstract class AbstractController {
    protected final PainelService painelService = new PainelServiceImpl();
    protected final FormularioService formularioService = new FormularioServiceImpl();
    protected final CategoriaService categoriaService = new CategoriaServiceImpl();
    protected final TransacaoService transacaoService = new TransacaoServiceImpl();

    public abstract void get();

    public abstract void post(Formulario formulario);

    public void patch(int id) {

    }

    public void delete(int id) {

    }

    public void patch(int id, Formulario formulario) {

    }

    public void validateCamposObrigatorios(Formulario formulario) throws CampoObrigatorioException {
        for (var component : formulario.getComponentes()) {
            if (component.isObrigatorio() && (Utils.isStringVazia(component.getInput()))) {
                throw new CampoObrigatorioException("Campo '" + component.getLabelCampo() + "' é obrigatório. Por favor, preencha-o.");
            }
        }
    }

    public void validateCamposNumericos(Formulario formulario) throws CampoInvalidoException {
        for (var component : formulario.getComponentes()) {
            if (!Utils.isStringVazia(component.getInput()) && component.getTipoCampoTexto() == TipoCampoTexto.NUMERO && !Utils.isNumero(component.getInput())) {
                throw new CampoInvalidoException("Campo '" + component.getLabelCampo() + "' é númerico. Por favor, preencha-o corretamente.");
            }
        }
    }
}
