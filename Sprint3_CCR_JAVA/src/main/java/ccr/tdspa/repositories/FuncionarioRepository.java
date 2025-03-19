package ccr.tdspa.repositories;

import ccr.tdspa.entities.Funcionario;
import ccr.tdspa.enums.Cargo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ccr.tdspa.infrastructure.DataBaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.io.File;
import java.sql.SQLException;
import java.util.*;

public class FuncionarioRepository implements CrudRepository<Funcionario> {
    public static Logger logger = LogManager.getLogger(FuncionarioRepository.class);
    private static final String SENHA_ADMIN = "CCR2025";

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
        var query = "UPDATE FUNCIONARIO SET NOME = ?, CARGO = ? WHERE ID = ?";

        try (var conn = DataBaseConfig.getConnection()) {
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, object.getNome());
            stmt.setString(2, object.getCargo().name());
            stmt.setInt(3, id);
            var result = stmt.executeUpdate();

            if (result > 0)
                logger.info("Funcionário atualizado com sucesso!");
            else
                logger.warn("Nenhum funcionário encontrado com este ID: " + id);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o funcionário!");
            logger.error(e.getMessage());
        }

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
        try (var connection = DataBaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while (result.next()) {
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
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Funcionario> listar() {
        return funcionarios.stream()
                .filter(f -> f.isDeleted())
                .toList();
    }

    @Override
    public Optional<Funcionario> searchById(int id) {
        var query = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
        try (var connection = DataBaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            var result = stmt.executeQuery();
            if (result.next()) {
                var funcionario = new Funcionario();
                funcionario.setId(result.getInt("id"));
                funcionario.setDeleted(result.getBoolean("deleted"));
                funcionario.setNome(result.getString("nome"));
                String cargoStr = result.getString("cargo");
                Cargo cargo = Cargo.valueOf(cargoStr.toUpperCase());
                funcionario.setCargo(cargo);
                return Optional.of(funcionario);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return Optional.empty();
    }

    //Função que cria um funcionário//Usada no menu
    public void criarFuncionario(FuncionarioRepository funcionarioRepository) {
        try {
            var sc = new Scanner(System.in);

            var id = 0;

            System.out.println("Digite o nome do Funcionário: ");
            var nome = sc.nextLine();

            System.out.println("Digite o Cargo do Funcionário");
            var cargoStr = sc.nextLine().toUpperCase();
            Cargo cargo = Cargo.valueOf(cargoStr);

            var funcionario = new Funcionario(id, false, nome, cargo);
            funcionarioRepository.create(funcionario);
            logger.info("Funcionário {},Criado com sucesso!", nome);
        } catch (Exception e) {
            System.out.println("Não foi possível criar o Funcionário");
            logger.error(e.getMessage());
        }
    }

    //Função que remove funcionário/Usado no menu
    public void removerFuncionario(FuncionarioRepository funcionarioRepository) {
        System.out.println("Digite o id do Funcionário que deseja deletar: ");
        var sc = new Scanner(System.in);
        var id = sc.nextInt();
        sc.nextLine();
        System.out.println("Deletando usuário com id... " + id);
        funcionarioRepository.deleteById(id);
    }

    //Função que lista funcionários/Usada no menu
    public void listarFuncionarios(FuncionarioRepository funcionarioRepository) {
        var sc = new Scanner(System.in);
        System.out.println("Insira a senha ADMIN: ");
        var senha = sc.nextLine();
        if (senha.equals(SENHA_ADMIN))
            System.out.println(funcionarioRepository.listarTodos());
        else
            System.out.println("Acesso negado!");
    }

    //Função que atualiza funcionário/Usada no menu
    public void atualizarFuncionario(FuncionarioRepository funcionarioRepository) {
        var sc = new Scanner(System.in);

        System.out.println("Insira o ID do Funcionário que deseja atualizar: ");
        var id = sc.nextInt();
        sc.nextLine();

        Optional<Funcionario> funcionarioOpt = funcionarioRepository.searchById(id);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            System.out.println("Nome atual: " + funcionario.getNome());
            System.out.println("Digite o novo nome (Ou pressione Enter para manter):");
            var novoNome = sc.nextLine();
            if (!novoNome.trim().isEmpty()) {
                funcionario.setNome(novoNome);
            }

            System.out.println("Cargo atual: " + funcionario.getCargo());
            System.out.println("Digite o novo cargo (Ou pressione Enter para manter):");
            var novoCargoStr = sc.nextLine().toUpperCase();
            if (!novoCargoStr.trim().isEmpty()) {
                try {
                    Cargo novoCargo = Cargo.valueOf(novoCargoStr);
                    funcionario.setCargo(novoCargo);
                } catch (IllegalArgumentException e) {
                    System.out.println("Cargo inválido! Mantendo o cargo anterior.");
                }
            }
            funcionarioRepository.update(id, funcionario);
        } else
            System.out.println("Funcionário não encontrado.");


    }

    //Verifica a existencia da lista
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
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    //Registra o funcionario em json
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

    //Função que cria um funcionário e exporta lista json
    public void montarListaFuncionarios(FuncionarioRepository funcionarioRepository) {
        Scanner sc = new Scanner(System.in);

        // Carregar a lista de funcionários do JSON no início
        List<Funcionario> listaFuncionarios = funcionarioRepository.carregarFuncionariosDoJson();

        label:
        while (true) {
            Random random = new Random();
            System.out.println("Digite 1 para prosseguir/ 0 para sair: ");
            var exit = sc.nextInt();

            switch (exit) {
                case 0:
                    break label;

                case 1:
                    var id = random.nextInt(100);
                    sc.nextLine();

                    System.out.println("Digite o nome do funcionário");
                    var nome = sc.nextLine();

                    System.out.println("Digite o cargo do funcionário -- (SEGURANCA) ou (TECNICODEMANUTENCAO)");
                    String cargoStr = sc.nextLine().toUpperCase();
                    Cargo cargo = Cargo.valueOf(cargoStr);


                    var funcionario = new Funcionario(id, false, nome, cargo);


                    listaFuncionarios.add(funcionario);

                    funcionarioRepository.registrarFuncJson(funcionario);


                    break;
            }
        }

    }

    public void menu(FuncionarioRepository funcionarioRepository) {

        label:
        while (true) {
            System.out.println("####################");
            System.out.println("Olá, Digite a opção que deseja executar: ");
            System.out.println("####################");
            System.out.println("1 - Criar Funcionário");
            System.out.println("2 - Excluir Funcionário");
            System.out.println("3 - Listar Funcionários -- (Admin/Acesso exclusivo)");
            System.out.println("4 - Buscar Funcionário por ID");
            System.out.println("5 - Atualizar Funcionário");
            System.out.println("6 - Montar lista (Json) de Funcionários");
            System.out.println("7 - Sair");

            var sc = new Scanner(System.in);
            var opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    criarFuncionario(funcionarioRepository);
                    System.out.println("Funcionário criado com sucesso!");
                    break;

                case 2:
                    removerFuncionario(funcionarioRepository);
                    System.out.println("Funcionário removido com sucesso!");
                    break;
                case 3:
                    listarFuncionarios(funcionarioRepository);
                    break;
                case 4:
                    System.out.println("Digite o ID do Funcionário");
                    var id = sc.nextInt();
                    sc.nextLine();
                    System.out.println(funcionarioRepository.searchById(id));
                    break;
                case 5:
                    atualizarFuncionario(funcionarioRepository);
                    System.out.println("Funcionário atualizado com sucesso!");
                    break;

                case 6:
                    montarListaFuncionarios(funcionarioRepository);
                    break;

                case 7:
                    System.out.println("####################");
                    System.out.println("Encerrando o Sistema...");
                    System.out.println("####################");
                    break label;


            }
        }
    }

}
