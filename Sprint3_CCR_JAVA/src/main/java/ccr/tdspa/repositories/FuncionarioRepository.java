package ccr.tdspa.repositories;

import ccr.tdspa.entities.Funcionario;

import java.util.ArrayList;
import java.util.List;

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
}
