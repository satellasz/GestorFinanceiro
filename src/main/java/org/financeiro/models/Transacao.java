package org.financeiro.models;

import org.financeiro.enums.ClassificacaoTransacao;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TRANSACAO")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private double valor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "classificacao")
    private ClassificacaoTransacao classificacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "dataTransacao")
    private LocalDate dataTransacao;

    public Transacao(long id, double valor, String descricao, Categoria categoria, ClassificacaoTransacao classificacao,
                     Usuario usuario, LocalDate dataTransacao) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.classificacao = classificacao;
        this.usuario = usuario;
        this.dataTransacao = dataTransacao;
    }

    public Transacao(double valor, String descricao, Categoria categoria, ClassificacaoTransacao classificacao,
                     Usuario usuario, LocalDate dataTransacao) {
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.classificacao = classificacao;
        this.usuario = usuario;
        this.dataTransacao = dataTransacao;
    }

    public Transacao() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ClassificacaoTransacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoTransacao classificacao) {
        this.classificacao = classificacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transacao that = (Transacao) obj;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
