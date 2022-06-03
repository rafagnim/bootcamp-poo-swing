package desafio.dominio;

import java.io.Serializable;

public abstract class Conteudo implements Serializable  {

    protected static final double XP_PADRAO = 10d;

    private String titulo;
    private String descricao;

    public abstract double calcularXp();

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return " -> Conteudo: [[" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                "]]";
    }
}
