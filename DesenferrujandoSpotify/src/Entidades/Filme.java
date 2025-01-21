package Entidades;

import java.util.Objects;
import java.util.StringJoiner;

public class Filme extends Midia{
    private String produtora;

    public Filme() {
    }

    public Filme(String titulo, String autor, int dataDeLancamento, int duracao, String produtora) {
        super(titulo, autor, dataDeLancamento, duracao);
        this.produtora = produtora;
    }

    @Override
    public void reproduzirMidia() {
        System.out.println("Titulo: " + getTitulo() + " Autor: " + getAutor() + " Duração: " + getDuracao()+"min" + " Data de lançamento:" + getDataDeLancamento() + " Produtora: " + produtora);
    }


    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Filme filme = (Filme) o;
        return Objects.equals(getDataDeLancamento(), filme.getDataDeLancamento()) && Objects.equals(getProdutora(), filme.getProdutora());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDataDeLancamento(), getProdutora());
    }

    @Override
    public String toString() {
        return "Filme: " + getTitulo() + "\n" + "Duração: " + getDuracao() + " minutos" ;
    }
}


