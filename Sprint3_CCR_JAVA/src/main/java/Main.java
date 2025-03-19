import ccr.tdspa.entities.Evento;
import ccr.tdspa.entities.Seguranca;
import ccr.tdspa.entities.TecnicoManutencao;
import ccr.tdspa.enums.Cargo;
import ccr.tdspa.enums.DescricaoEvento;
import ccr.tdspa.repositories.EventoRepository;
import ccr.tdspa.repositories.FuncionarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Sistema iniciando...");

        var funcionarioRepository = new FuncionarioRepository();
        var eventoRepository = new EventoRepository();

        var evento1 = new Evento(1, false, DescricaoEvento.MANUTENCAO_ELETRICA);
        var tecnico = new TecnicoManutencao(3, false, "Roberto", Cargo.TECNICO_DE_MANUTENCAO);

        //funcionarioRepository.create(tecnico);
        //eventoRepository.create(evento1);
        //funcionarioRepository.montarListaFuncionarios(funcionarioRepository);
        funcionarioRepository.menu(funcionarioRepository);









        logger.info("Sistema finalizando...");
    }
}

