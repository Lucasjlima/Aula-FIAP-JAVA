import ccr.tdspa.entities.Evento;
import ccr.tdspa.entities.Funcionario;
import ccr.tdspa.entities.Seguranca;
import ccr.tdspa.entities.TecnicoManutencao;
import ccr.tdspa.enums.Cargo;
import ccr.tdspa.enums.DescricaoEvento;
import ccr.tdspa.infrastructure.AplicationTestConfig;
import ccr.tdspa.repositories.EventoRepository;
import ccr.tdspa.repositories.FuncionarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Sistema iniciando...");
        AplicationTestConfig.testAplication(args);

        var funcionarioRepository = new FuncionarioRepository();
        var funcionario = new Funcionario(1,false,"Adryan",Cargo.TECNICODEMANUTENCAO);
        var evento = new Evento(1,false,DescricaoEvento.MANUTENCAO_ELETRICA);
        funcionarioRepository.menu(funcionarioRepository);

        funcionario.menuFunc(funcionario,evento);









        logger.info("Sistema finalizando...");
    }
}

