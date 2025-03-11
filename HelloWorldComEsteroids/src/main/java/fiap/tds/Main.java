package fiap.tds;

import fiap.tds.entities.Colecao;
import fiap.tds.infraestrutura.DatabaseConfig;
import fiap.tds.repositories.ColecaoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String SENHA_MESTRE = "SENHA123";
    public static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Sistema Iniciando...");
        logger.info("Testando banco de dados...");
        try {
            var connection = DatabaseConfig.getConnection();
            logger.info("A conexão de dados foi estabelecida com sucesso!");
            connection.close();
            var colecaoRepository = new ColecaoRepository();
            System.out.println("-------------------------------------------------------------");
            System.out.println("Bem vindo ao sistemas de cartas");
            System.out.println("-------------------------------------------------------------");

            label:
            while (true) {
                System.out.println("Digite a opção desejada:");
                System.out.println("1 - Adicionar coleção");
                System.out.println("2 - Listar coleções");
                System.out.println("3 - Remover coleção");
                System.out.println("4 - Listar todas as coleções (ADMIN APENAS)");
                System.out.println("5 - Exportar arquivo de coleções");
                System.out.println("6 - Importar");
                System.out.println("7 - Sair");
                var scan = new Scanner(System.in);
                var opcao = scan.nextInt();
                scan.nextLine();
                switch (opcao) {
                    case 1:
                        CadastrarColecao(colecaoRepository);
                        break;
                    case 2:
                        System.out.println(colecaoRepository.listarTodos());
                        break;
                    case 3:
                        RemoverColecao(colecaoRepository);
                        break;
                    case 4:
                        ListarTodasColecoes(colecaoRepository);
                        break;
                    case 5:
                        colecaoRepository.exportarParaJson();
                        break;
                    case 6:
                        System.out.println("Digite o nome do arquivo: ");
                        var nomeDoArquivo = scan.nextLine();
                        colecaoRepository.importar(nomeDoArquivo);
                        break;
                    case 7:
                        break label;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            }
        }
        catch (SQLException e){
            logger.fatal("Erro ao conectar com banco de dados", e);
            System.out.println("Não foi possível conectar com banco de dados");
        }
        logger.info("Sistema finalizando...");
    }

    public static void CadastrarColecao(ColecaoRepository repository) {
        try {
            var scan = new Scanner(System.in);
            System.out.println("Digite o id da coleção");
            var id = scan.nextInt();
            scan.nextLine();
            System.out.println("Digite o nome da coleção");
            var nome = scan.nextLine();
            System.out.println("Digite o código da coleção");
            var codigo = scan.nextLine();
            System.out.println("Digite a data de lançamento da coleção");
            var dataLancamento = scan.nextLine();
            var colecao = new Colecao(nome, codigo, dataLancamento);
            colecao.setId(id);
            repository.adicionar(colecao);
            logger.info("Colecão registrada com sucesso {}", colecao);
        }
        catch(Exception e){
            System.out.println("Campo com valor incorreto");
            logger.error("Erro ao cadastrar coleção", e);
        }
    }

    public static void RemoverColecao(ColecaoRepository repository){
        System.out.println("Digite o id da coleção que deseja remover");
        var scan = new Scanner(System.in);
        var id = scan.nextInt();
        scan.nextLine();
        repository.deleteById(id);
    }

    public static void ListarTodasColecoes(ColecaoRepository repository){
        var scan = new Scanner(System.in);
        System.out.println("Digite a senha de administrador");
        var senha = scan.nextLine();
        if (senha.equals(SENHA_MESTRE))
            System.out.println(repository.listarTodos());
        else
            System.out.println("Acesso não autorizado");
    }


}