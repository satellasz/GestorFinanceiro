package org.financeiro.componentes;

import org.financeiro.enums.TipoCampoTexto;

import javax.swing.*;
import java.awt.*;

public class CampoTexto extends JPanel {
    private int id;
    private String nome;
    private boolean obrigatorio;
    private int altura;
    private int largura;
    private String mensagemErro;
    private TipoCampoTexto tipoCampoTexto;
    private JTextField textField;

    public CampoTexto(int id, String nome, int altura, int largura, TipoCampoTexto tipoCampoTexto, String mensagemErro, boolean obrigatorio) {
        this.id = id;
        this.nome = nome;
        this.obrigatorio = obrigatorio;
        this.altura = altura;
        this.largura = largura;
        this.mensagemErro = mensagemErro;
        this.tipoCampoTexto = tipoCampoTexto;

        this.textField = new JTextField();
        this.textField.setSize(new Dimension(altura, largura));

        this.add(textField);
    }

    public CampoTexto(int id, String nome, int altura, int largura, TipoCampoTexto tipoCampoTexto) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.largura = largura;
        this.tipoCampoTexto = tipoCampoTexto;

        this.textField = new JTextField();
        textField.setColumns(20);

        this.add(textField);
    }

    public TipoCampoTexto getTipoCampoTexto() {
        return tipoCampoTexto;
    }

    public void setTipoCampoTexto(TipoCampoTexto tipoCampoTexto) {
        this.tipoCampoTexto = tipoCampoTexto;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
