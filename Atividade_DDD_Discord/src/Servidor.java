import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Servidor {
    private String nome;
    private List<Usuario> usuarios;
    private List<Mensagem> mensagens;

    public Servidor(String nome) {
        this.nome = nome;
        this.usuarios = new ArrayList<>();
        this.mensagens = new ArrayList<>();
    }


    public void adicionarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void removerUsuario(Usuario usuario){
        this.usuarios.remove(usuario);
    }

    public void enviarMensagem(Mensagem mensagem){
        this.mensagens.add(mensagem);
    }

    public List<Mensagem> filtrarMensagensPorAutor(String nomeAutor){
        return mensagens.stream()
                .filter(m -> m.getAutor().getNome().equals(nomeAutor))
                .collect(Collectors.toList());
    }

    public List<Mensagem> filtrarPorPalavraChave(String palavraChave){
        return mensagens.stream()
                .filter(m -> m.getConteudo().contains(palavraChave))
                .collect(Collectors.toList());
    }

    public Optional<Usuario> usuarioComMaisMensagens() {
        return mensagens.stream()
                .collect(Collectors.groupingBy(Mensagem::getAutor, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public List<Usuario> listarUsuariosPorPalavraChave(String palavraChave) {
        return mensagens.stream()
                .filter(mensagem -> mensagem.getConteudo().contains(palavraChave))
                .map(Mensagem::getAutor)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<Usuario, List<Mensagem>> agruparPorAutor(){
        return mensagens.stream()
                .collect(Collectors.groupingBy(Mensagem::getAutor));
    }

    public Map<Usuario, Long> contandoMensagensPeloUsuario(){
        return mensagens.stream()
                .collect(Collectors.groupingBy(Mensagem::getAutor, Collectors.counting()));
    }



    public long contarMensagens(){
        return mensagens.stream().count();
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
