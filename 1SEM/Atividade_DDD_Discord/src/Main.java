import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        var usuario1 = new Usuario(1, "BiaScott");
        var usuario2 = new Usuario(2, "Bob");
        var usuario3 = new Usuario(3, "Marcelo");
        var usuario4 = new Usuario(4, "Jack");

        var novoServidor = new Servidor("Iniciantes Na Programação");

        novoServidor.adicionarUsuario(usuario1);
        novoServidor.adicionarUsuario(usuario2);
        novoServidor.adicionarUsuario(usuario3);
        novoServidor.adicionarUsuario(usuario4);

        novoServidor.enviarMensagem(new Mensagem("Bom dia pessoal!!", usuario2));
        novoServidor.enviarMensagem(new Mensagem("Iai Bob como é que ta mano? tirou quanto na prova de ontem?", usuario1));
        novoServidor.enviarMensagem(new Mensagem("Mano preciso estudar mais Java, foi osso tirei 2", usuario2));
        novoServidor.enviarMensagem(new Mensagem("Fica tranquilo ainda tem a segunda prova, da pra tu recuperar", usuario1));
        novoServidor.enviarMensagem(new Mensagem("Slv galera!, Po seis viram o novo filme do Coringa?, só falam mal vei", usuario3));
        novoServidor.enviarMensagem(new Mensagem("Iai rpz, eu vi Marcelo, mas eu acho que não deve ser tão ruim assim, vamo assistir primeiro né", usuario4));
        novoServidor.enviarMensagem(new Mensagem("Bom mesmo é Batman vs Superman...", usuario3));
        novoServidor.enviarMensagem(new Mensagem("Oloko Marcelão kkk", usuario2));

        System.out.println("--------------Mensagens do Bob------------");
        List<Mensagem> mensagensBob = novoServidor.filtrarMensagensPorAutor("Bob");
        mensagensBob.forEach(mensagem -> System.out.println(mensagem.getConteudo()));

        System.out.println("--------------Mensagens do Marcelo------------");
        List<Mensagem> mensagensMarcelo = novoServidor.filtrarMensagensPorAutor("Marcelo");
        mensagensMarcelo.forEach(mensagem -> System.out.println(mensagem.getConteudo()));

        System.out.println("--------------Mensagem Chave------------");
        List<Mensagem> mensagemChaveMarcelo = novoServidor.filtrarPorPalavraChave("Coringa");
        mensagemChaveMarcelo.forEach(mensagem -> System.out.println(mensagem.getConteudo()));

        System.out.println("--------------Total de  mensagens do servidor------------");
        long totalMensagens = novoServidor.contarMensagens();
        System.out.println("Total: " + totalMensagens);

        System.out.println("--------------Usuário com mais mensagens------------");
        Optional<Usuario> filtrarUsuarioMaisMensagens = novoServidor.usuarioComMaisMensagens();
        filtrarUsuarioMaisMensagens.ifPresent(usuario -> System.out.println( "Usuário: "+ usuario.getNome()));

        System.out.println("--------------Filtrando mensagens por palavra chave de usuário------------");
        List<Usuario> usuariosChaves = novoServidor.listarUsuariosPorPalavraChave("Iai");
        usuariosChaves.forEach(usuario -> System.out.println(usuario.getNome()));

        System.out.println("--------------Agrupando Por Autor------------");
        Map<Usuario, List<Mensagem>> mensagensPorAutor = novoServidor.agruparPorAutor();
        mensagensPorAutor.forEach((usuario, mensagens) -> {
            System.out.println(usuario.getNome() + " enviou: ");
            mensagens.forEach(mensagem -> System.out.println(" - " + mensagem.getConteudo()));
        });

        System.out.println("--------------Contando Mensagens por Usuário------------");
        Map<Usuario, Long> contandoPorUsuario = novoServidor.contandoMensagensPeloUsuario();
        contandoPorUsuario.forEach((usuario, contagem) -> {
            System.out.println(usuario.getNome() + " enviou: " + contagem + " mensagens.");
        });

    }
}
