package ccr.tdspa.repositories;

import ccr.tdspa.entities.Funcionario;
import ccr.tdspa.entities.Seguranca;
import ccr.tdspa.entities.TecnicoManutencao;
import ccr.tdspa.enums.Cargo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import infrastructure.DataBaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FuncionarioRepository implements CrudRepository<Funcionario> {
    public static Logger logger = LogManager.getLogger(FuncionarioRepository.class);

    List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public void create(Funcionario object) {
            var query = "INSERT INTO FUNCIONARIO (DELETED,NOME,CARGO) VALUES (?,?,?) ";
            try(var conn = DataBaseConfig.getConnection())
            {
                var stmt = conn.prepareStatement(query);
                stmt.setBoolean(1,false);
                stmt.setString(2,object.getNome());
                stmt.setString(3,object.getCargo().name());
                var result = stmt.executeUpdate();
                if(result > 0)
                    logger.info("Funcionário adicionado com sucesso!");
            }
        catch (SQLException e){
                System.out.println("Conexão com DataBase NEGADA!");
                logger.error(e.getMessage());
        }
    }

    @Override
    public void update(int id, Funcionario object) {
            for(Funcionario f: funcionarios)
                if(f.getId() == id)
                    f = object;
    }

    @Override
    public void delete(Funcionario object) {
            funcionarios.remove(object);
    }

    @Override
    public void deleteById(int id) {
            var set = funcionarios.stream()
                    .filter(f -> f.getId() == id)
                    .findFirst();
            set.ifPresent(f -> f.setDeleted(true));
    }

    @Override
    public List<Funcionario> listarTodos() {
        return funcionarios;
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

    public void registrarFuncJson(List<Funcionario> funcionarios) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            objectMapper.writeValue(new File("funcionarios.json"),funcionarios);
            logger.info("Funcionario criado com sucesso! em funcionarios.json");
            System.out.println("Funcionario criados com sucesso!");
        }
        catch(Exception e){
            System.out.println("Erro ao converter objeto funcionario em json");
            logger.error(e.getMessage());
        }
    }


    public void montarListaFuncionarios(FuncionarioRepository funcionarioRepository) {
        Scanner sc = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Digite 1 para prosseguir/ 0 para sair: ");
            var exit = sc.nextInt();
                switch (exit) {
                    case 0:
                        break label;
                    case 1:
                    var id = 0;
                    sc.nextLine();
                    System.out.println("Digite o nome do funcionário");
                    var nome = sc.nextLine();
                    System.out.println("Digite o cargo do funcionário");
                    String cargoStr = sc.nextLine().toUpperCase();
                    Cargo cargo = Cargo.valueOf(cargoStr);
                    var funcionario = new Funcionario(id, false, nome, cargo);
                    funcionarioRepository.create(funcionario);
                    funcionarioRepository.registrarFuncJson(funcionarioRepository.listarTodos());

                    break;

                }
        }
    }
}
