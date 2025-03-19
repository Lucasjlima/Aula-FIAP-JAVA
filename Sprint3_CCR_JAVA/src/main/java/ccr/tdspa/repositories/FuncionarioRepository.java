package ccr.tdspa.repositories;

import ccr.tdspa.entities.Funcionario;
import ccr.tdspa.entities.Seguranca;
import ccr.tdspa.entities.TecnicoManutencao;
import ccr.tdspa.enums.Cargo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import infrastructure.DataBaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.sql.SQLException;
import java.util.*;

public class FuncionarioRepository implements CrudRepository<Funcionario> {
    public static Logger logger = LogManager.getLogger(FuncionarioRepository.class);

    List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public void create(Funcionario object) {
        var query = "INSERT INTO FUNCIONARIO (DELETED,NOME,CARGO) VALUES (?,?,?) ";
        try (var conn = DataBaseConfig.getConnection()) {
            var stmt = conn.prepareStatement(query);
            stmt.setBoolean(1, false);
            stmt.setString(2, object.getNome());
            stmt.setString(3, object.getCargo().name());
            var result = stmt.executeUpdate();
            if (result > 0)
                logger.info("Funcionário adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Conexão com DataBase NEGADA!");
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(int id, Funcionario object) {
        for (Funcionario f : funcionarios)
            if (f.getId() == id)
                f = object;
    }

    @Override
    public void delete(Funcionario object) {
        object.setDeleted(true);
    }

    @Override
    public void deleteById(int id) {
        var query = "DELETE FROM FUNCIONARIO WHERE ID = ?";
        try (var conn = DataBaseConfig.getConnection()) {
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            var result = stmt.executeUpdate();

            if (result > 0) {
                logger.info("Funcionário removido com sucesso!");

            } else {
                System.out.println("Nenhum funcionário encontrado com esse ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Funcionário: " + e.getMessage());
            logger.error("Erro ao deletar Funcionário", e);
        }
    }


    @Override
    public List<Funcionario> listarTodos() {
        var funcionariosDb = new ArrayList<Funcionario>();
        var query = "SELECT * FROM FUNCIONARIO";
        try(var connection = DataBaseConfig.getConnection())
        {
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while(result.next())
            {
                var funcionario = new Funcionario();
                funcionario.setId(result.getInt("id"));
                funcionario.setDeleted(result.getBoolean("deleted"));
                funcionario.setNome(result.getString("nome"));
                String cargoStr = result.getString("cargo");
                Cargo cargo = Cargo.valueOf(cargoStr.toUpperCase());
                funcionario.setCargo(cargo);
                funcionariosDb.add(funcionario);
            }
            return funcionariosDb;
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Funcionario> listar() {
        return funcionarios.stream()
                .filter(f -> f.isDeleted())
                .toList();
    }

    @Override
    public Optional<Funcionario> searchById(int id) {
        return Optional.empty();
    }

    public void removerFuncionario(FuncionarioRepository funcionarioRepository) {
        System.out.println("Digite o id do Funcionário que deseja deletar: ");
        var sc = new Scanner(System.in);
        var id = sc.nextInt();
        sc.nextLine();
        System.out.println("Deletando usuário com id... " + id);
        funcionarioRepository.deleteById(id);
    }

    public List<Funcionario> carregarFuncionariosDoJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("funcionarios.json");

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(file, new TypeReference<List<Funcionario>>() {
            });
        } catch (Exception e) {
            System.out.println("Erro ao carregar funcionários do JSON!");
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }


    public void registrarFuncJson(Funcionario novoFuncionario) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Funcionario> funcionarios = carregarFuncionariosDoJson();

        funcionarios.add(novoFuncionario);

        try {
            objectMapper.writeValue(new File("funcionarios.json"), funcionarios);
            logger.info("Funcionários salvos no JSON!");
            System.out.println("Funcionário salvo no JSON!");
        } catch (Exception e) {
            System.out.println("Erro ao converter objeto funcionário em JSON");
            logger.error(e.getMessage());
        }
    }


    public void montarListaFuncionarios(FuncionarioRepository funcionarioRepository) {
        Scanner sc = new Scanner(System.in);

        // Carregar a lista de funcionários do JSON no início
        List<Funcionario> listaFuncionarios = funcionarioRepository.carregarFuncionariosDoJson();

        label:
        while (true) {
            System.out.println("Digite 1 para prosseguir/ 0 para sair: ");
            var exit = sc.nextInt();

            switch (exit) {
                case 0:
                    break label;  // Sai do loop

                case 1:
                    var id = 0;  // Defina o ID conforme necessário
                    sc.nextLine();  // Limpar o buffer do Scanner

                    System.out.println("Digite o nome do funcionário");
                    var nome = sc.nextLine();

                    System.out.println("Digite o cargo do funcionário");
                    String cargoStr = sc.nextLine().toUpperCase();
                    Cargo cargo = Cargo.valueOf(cargoStr);

                    // Criar o novo funcionário
                    var funcionario = new Funcionario(id, false, nome, cargo);

                    // Adicionar à lista carregada do JSON
                    listaFuncionarios.add(funcionario);

                    // Inserir no banco de dados
                    funcionarioRepository.create(funcionario);

                    // Salvar todos os funcionários no arquivo JSON
                    funcionarioRepository.registrarFuncJson(funcionario);

                    // Mostrar os funcionários carregados (opcional, só para ver o que está sendo salvo)
                    System.out.println(listaFuncionarios);
                    break;
            }
        }

        System.out.println("Funcionário criado com sucesso!");
    }

    public void menu(FuncionarioRepository funcionarioRepository) {

        label:
        while (true) {
            System.out.println("####################");
            System.out.println("Olá, Digite a opção que deseja executar: ");
            System.out.println("####################");
            System.out.println("1 - Excluir Funcionário");
            System.out.println("2 - Sair");

            var sc = new Scanner(System.in);
            var opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    removerFuncionario(funcionarioRepository);
                    System.out.println("Funcionário removido com sucesso!");
                    break;
                case 2:
                    System.out.println("Encerrando o Sistema...");
                    break label;


            }
        }
    }

}
