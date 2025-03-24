package org.financeiro.controllers;

import org.financeiro.componentes.Formulario;
import org.financeiro.exceptions.CampoObrigatorioException;
import org.financeiro.repositories.categoria.CategoriaRepositoryImpl;
import org.financeiro.services.PainelService;
import org.financeiro.services.PainelServiceImpl;
import org.financeiro.services.categoria.CategoriaService;
import org.financeiro.services.categoria.CategoriaServiceImpl;

public abstract class AbstractController {
    protected final PainelService painelService = new PainelServiceImpl();
    protected final CategoriaService categoriaService = new CategoriaServiceImpl(new CategoriaRepositoryImpl());

    public abstract void get();

    public abstract void post(Formulario formulario);

    public void patch(Formulario formulario) {
    }

    public void delete(Formulario formulario) {
    }

    public void validate(Formulario formulario) throws CampoObrigatorioException {
        for (var component : formulario.getComponentes()) {
            if (component.isObrigatorio() && (component.getInput() == null || component.getInput().trim().isEmpty())) {
                throw new CampoObrigatorioException("Campo " + component.getNome() + " é obrigatório. Por favor, preencha-o.");
            }
        }
    }
}
