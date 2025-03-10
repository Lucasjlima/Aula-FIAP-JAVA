package ccr.tdspa.repositories;

import ccr.tdspa.entities.Funcionario;
import ccr.tdspa.entities.Seguranca;
import ccr.tdspa.entities.TecnicoManutencao;
import ccr.tdspa.enums.Cargo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioRepository implements CrudRepository<Funcionario> {

    List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public void create(Funcionario object) {
            funcionarios.add(object);
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

    public void montarListaFuncionarios() {
        Scanner sc = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Digite 1 para prosseguir/ 0 para sair: ");
            var exit = sc.nextInt();
            if(exit == 0){
                break label;
            } else if (exit == 1) {
                System.out.println("Digite o id do funcionário");
                var id = sc.nextInt();
                sc.nextLine();
                System.out.println("Digite o nome do funcionário");
                var nome = sc.nextLine();
                System.out.println("Digite o cargo do funcionário");
                var cargo = sc.nextLine();
                System.out.println("Digite true para instanciar o funcionário");
                var ativo = sc.nextBoolean();
                var funcionario = new Funcionario(id,ativo,nome,Cargo.valueOf(cargo));

            }

        }
    }
}
