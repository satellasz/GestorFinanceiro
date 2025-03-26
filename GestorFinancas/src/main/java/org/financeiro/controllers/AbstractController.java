package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.repositories.categoria.CategoriaRepositoryImpl;
import org.financeiro.repositories.transacao.TransacaoRepositoryImpl;
import org.financeiro.services.FormularioService;
import org.financeiro.services.FormularioServiceImpl;
import org.financeiro.services.PainelService;
import org.financeiro.services.PainelServiceImpl;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;
import org.financeiro.services.transacao.TransacaoService;
import org.financeiro.services.transacao.TransacaoServiceImpl;

public abstract class AbstractController {
    protected final PainelService painelService = new PainelServiceImpl();
    protected final FormularioService formularioService = new FormularioServiceImpl();
    protected final CategoriaService categoriaService = new CategoriaServiceImpl(new CategoriaRepositoryImpl());
    protected final TransacaoService transacaoService = new TransacaoServiceImpl(new TransacaoRepositoryImpl());

    public abstract void get();

    public abstract void post(Formulario formulario);

    public void patch(int id) {

    }

    public void delete(int id) {

    }

    public void patch(int id, Formulario formulario) {

    }

    public void validate(Formulario formulario) throws CampoObrigatorioException {
        for (var component : formulario.getComponentes()) {
            if (component.isObrigatorio() && (component.getInput() == null || component.getInput().trim().isEmpty())) {
                throw new CampoObrigatorioException("Campo '" + component.getLabelCampo() + "' é obrigatório. Por favor, preencha-o.");
            }
        }
    }
}
