package Entidades;

import java.util.Objects;

public abstract class  Midia {
    private String titulo;
    private String autor;
    private int dataDeLancamento;
    private int duracao;

    public Midia() {
    }

    public Midia(String titulo, String autor, int dataDeLancamento , int duracao) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataDeLancamento = dataDeLancamento;
        this.duracao = duracao;
    }

    public void reproduzirMidia(){
        System.out.println("Reproduzindo mídia: " + titulo);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(int dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Midia midia = (Midia) o;
        return getDuracao() == midia.getDuracao() && Objects.equals(getTitulo(), midia.getTitulo()) && Objects.equals(getAutor(), midia.getAutor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getAutor(), getDuracao());
    }

    @Override
    public String toString() {
        return """
                \
                Midia{
                    titulo='$titulo',
                    autor='$autor',
                    dataDeLancamento='$dataDeLancamento',
                    duracao=$duracao
                }""";
    }
}
