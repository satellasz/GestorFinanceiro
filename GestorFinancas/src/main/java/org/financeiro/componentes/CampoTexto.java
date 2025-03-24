package org.financeiro.componentes;

import org.financeiro.enums.TipoCampoTexto;

import javax.swing.*;
import java.awt.*;

public class CampoTexto extends JPanel implements Input {
    private final int id;
    private final String nome;
    private boolean obrigatorio;
    private String mensagemErro;
    private final TipoCampoTexto tipoCampoTexto;
    private final JTextField textField;

    public CampoTexto(int id, String nome, TipoCampoTexto tipoCampoTexto, String mensagemErro, boolean obrigatorio) {
        this(id, nome, tipoCampoTexto);

        this.mensagemErro = mensagemErro;
        this.obrigatorio = obrigatorio;
    }

    public CampoTexto(int id, String nome, TipoCampoTexto tipoCampoTexto) {
        this.id = id;
        this.nome = nome;
        this.tipoCampoTexto = tipoCampoTexto;

        this.setLayout(new BorderLayout(100, 0));

        JLabel label = new JLabel(nome);
        this.textField = new JTextField();

        this.setPreferredSize(new Dimension(500, 25));
        this.add(label, BorderLayout.WEST);
        this.add(this.textField, BorderLayout.CENTER);
        this.textField.setName(nome);
    }

    public TipoCampoTexto getTipoCampoTexto() {
        return tipoCampoTexto;
    }

    @Override
    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    @Override
    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String getInput() {
        return textField.getText();
    }
}
