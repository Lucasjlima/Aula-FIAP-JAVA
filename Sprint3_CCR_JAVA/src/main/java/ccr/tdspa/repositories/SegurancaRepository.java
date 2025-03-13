package ccr.tdspa.repositories;

import ccr.tdspa.entities.Seguranca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SegurancaRepository implements CrudRepository<Seguranca>{

    List<Seguranca> segurancas = new ArrayList<>();

    @Override
    public void create(Seguranca object) {
        segurancas.add(object);
    }

    @Override
    public void update(int id, Seguranca object) {
        for(Seguranca s: segurancas)
            if(s.getId() == id)
                s = object;
    }

    @Override
    public void delete(Seguranca object) {
            segurancas.remove(object);
    }

    @Override
    public void deleteById(int id) {
        var set = segurancas.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        set.ifPresent(s -> s.setDeleted(true));
    }

    @Override
    public List<Seguranca> listar() {
        return segurancas.stream()
                .filter(s -> s.isDeleted())
                .toList();
    }

    @Override
    public Optional<Seguranca> searchById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Seguranca> listarTodos() {
        return segurancas;
    }
}
