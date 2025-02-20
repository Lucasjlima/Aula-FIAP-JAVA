package ccr.tdspa.repositories;

import ccr.tdspa.entities.GerirFuncionarios;

import java.util.ArrayList;
import java.util.List;

public class GerirFuncionariosRepository implements CrudRepository<GerirFuncionarios>{
    List<GerirFuncionarios> gerenciadorF = new ArrayList();

    @Override
    public void create(GerirFuncionarios object) {
            gerenciadorF.add(object);
    }

    @Override
    public void update(int id, GerirFuncionarios object) {
        for (GerirFuncionarios g: gerenciadorF)
            if (g.getId() == id)
                g = object;
    }

    @Override
    public void delete(GerirFuncionarios object) {
            gerenciadorF.remove(object);
    }

    @Override
    public void deleteById(int id) {
        var set = gerenciadorF.stream()
                .filter(g -> g.getId() == id)
                .findFirst();
        set.ifPresent(g -> g.setDeleted(true));
    }

    @Override
    public List<GerirFuncionarios> listar() {
        return gerenciadorF.stream()
                .filter(g -> g.isDeleted())
                .toList();
    }

    @Override
    public List<GerirFuncionarios> listarTodos() {
        return gerenciadorF;
    }
}
