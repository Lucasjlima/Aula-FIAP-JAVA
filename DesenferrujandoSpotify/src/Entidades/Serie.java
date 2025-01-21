package Entidades;

import java.util.Objects;

public class Serie extends Midia {
    private int temporadas;
    private int totalDeEpisodios;
    private String plataformaDeStreaming;

    public Serie() {
    }

    public Serie(String titulo, String autor, int dataDeLancamento, int duracao, int temporadas, int totalDeEpisodios, String plataformaDeStreaming) {
        super(titulo, autor, dataDeLancamento, duracao);
        this.temporadas = temporadas;
        this.totalDeEpisodios = totalDeEpisodios;
        this.plataformaDeStreaming = plataformaDeStreaming;
    }

    @Override
    public void reproduzirMidia() {
        System.out.println("Titulo: " + getTitulo() + "Autor: " + getAutor() + "Duração: " + getDuracao() + "Data de lançamento:" + getDataDeLancamento() + "Temporadas: " + temporadas + "Total de episódios: " + totalDeEpisodios + "\n" + "Plataforma: " + plataformaDeStreaming + "\n" );
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getTotalDeEpisodios() {
        return totalDeEpisodios;
    }

    public void setTotalDeEpisodios(int totalDeEpisodios) {
        this.totalDeEpisodios = totalDeEpisodios;
    }

    public String getPlataformaDeStreaming() {
        return plataformaDeStreaming;
    }

    public void setPlataformaDeStreaming(String plataformaDeStreaming) {
        this.plataformaDeStreaming = plataformaDeStreaming;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Serie serie = (Serie) o;
        return getTemporadas() == serie.getTemporadas() && getTotalDeEpisodios() == serie.getTotalDeEpisodios() && Objects.equals(getPlataformaDeStreaming(), serie.getPlataformaDeStreaming());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTemporadas(), getTotalDeEpisodios(), getPlataformaDeStreaming());
    }

    @Override
    public String toString() {
        return "Serie: " +getTitulo() +
                " temporadas= " + temporadas +
                ", totalDeEpisodios= " + totalDeEpisodios +
                ", plataforma de streaming= '" + plataformaDeStreaming + '\''
                ;
    }
}
