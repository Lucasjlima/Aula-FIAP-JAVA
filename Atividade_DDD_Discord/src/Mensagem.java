import java.time.LocalDateTime;

public class Mensagem {
    private String conteudo;
    private Usuario autor;
    private LocalDateTime dataHora;

    public Mensagem(String conteudo, Usuario autor) {
        this.conteudo = conteudo;
        this.autor = autor;
        this.dataHora = LocalDateTime.now();
    }

    public String getConteudo() {
        return conteudo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }


}
