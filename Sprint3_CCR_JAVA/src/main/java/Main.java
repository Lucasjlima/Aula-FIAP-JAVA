import ccr.tdspa.entities.Evento;
import ccr.tdspa.entities.Seguranca;
import ccr.tdspa.entities.TecnicoManutencao;
import ccr.tdspa.enums.Cargo;
import ccr.tdspa.repositories.EventoRepository;
import ccr.tdspa.repositories.FuncionarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Sistema iniciando...");

        var funcionarioRepository = new FuncionarioRepository();

        var eventoRepository= new EventoRepository();

        var evento1 = new Evento(1,true,"Briga entre torcedores, Linha Amarela, Estação: Butantã ");

        var seguranca = new Seguranca(3,false,"Roberto", Cargo.SEGURANCA);

        funcionarioRepository.create(seguranca);

        eventoRepository.create(evento1);

        // Atualizando as informações do segurança ja criado
        funcionarioRepository.update
                (3,seguranca = new Seguranca(4,false,"Lucas", Cargo.SEGURANCA));
        seguranca.resolverEvento(seguranca,evento1,eventoRepository);

        System.out.println(eventoRepository.listarTodos());

        logger.info("Sistema finalizando...");
    }
}

