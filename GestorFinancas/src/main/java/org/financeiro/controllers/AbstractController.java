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
import org.financeiro.services.usuario.LoginService;
import org.financeiro.services.usuario.LoginServiceImpl;
import org.financeiro.services.usuario.UsuarioService;
import org.financeiro.services.usuario.UsuarioServiceImpl;
import org.financeiro.utils.Utils;

public abstract class AbstractController {
    protected final PainelService painelService = new PainelServiceImpl();
    protected final FormularioService formularioService = new FormularioServiceImpl();
    protected final CategoriaService categoriaService = new CategoriaServiceImpl();
    protected final TransacaoService transacaoService = new TransacaoServiceImpl();
    protected final UsuarioService usuarioService = new UsuarioServiceImpl();
    protected final LoginService loginService = new LoginServiceImpl();

    public abstract void get();

    public abstract void post(Formulario formulario);

    public void patch(int id) {

    }

    public void delete(int id) {

    }

    public void validateCampos(Formulario formulario) throws CampoObrigatorioException, CampoInvalidoException {
        for (var component : formulario.getComponentes()) {
            if (component.isObrigatorio() && (Utils.isStringVazia(component.getInput()))) {
                throw new CampoObrigatorioException("Campo '" + component.getLabelCampo() + "' é obrigatório. Por favor, preencha-o.");
            }

            if (!Utils.isStringVazia(component.getInput()) && component.getTipoCampoTexto() == TipoCampoTexto.NUMERO && !Utils.isNumero(component.getInput())) {
                throw new CampoInvalidoException("Campo '" + component.getLabelCampo() + "' é númerico. Por favor, preencha-o corretamente.");
            }
        }
    }
}
