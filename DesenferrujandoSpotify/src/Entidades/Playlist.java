package Entidades;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Playlist {
    private String nome;
    private List<Midia> midias;

    public Playlist() {
    }

    public Playlist(String nome) {
        this.nome = nome;
        this.midias = new ArrayList<>();
    }

    public void adicionarMidia(Midia midia) {
        midias.add(midia);
    }

    public void removerMidia(Midia midia) {
        midias.remove(midia);
    }

    public List<Midia> filtrarObraPorMaiorDuracao(int duracao) {
        return midias.stream()
                .filter(m -> m.getDuracao() > duracao)
                .toList();
    }

    public List<Midia> filtarPorNome(String letra) {
        return midias.stream()
                .filter(m -> m.getTitulo().contains(letra))
                .toList();
    }

    public List<Midia> filtraPorLancamento(int lancamento){
        return midias.stream()
                .filter(f -> f.getDataDeLancamento() == lancamento)
                .collect(Collectors.toList());
    }

    public List<Midia> filtarPorStreaming(String plataforma){
        return midias.stream()
                .filter(midia -> midia instanceof Serie)
                .map(midia -> (Serie) midia)
                .filter(f -> f.getPlataformaDeStreaming().equals(plataforma))
                .collect(Collectors.toList());
    }


}
