package ccr.tdspa.repositories;

import ccr.tdspa.entities.Limpeza;

import java.util.ArrayList;
import java.util.List;

public class LimpezaRepository implements CrudRepository<Limpeza>{

    List<Limpeza> limpezas = new ArrayList<>();

    @Override
    public void create(Limpeza object) {
        limpezas.add(object);
    }

    @Override
    public void update(int id, Limpeza object) {
            for(Limpeza l: limpezas)
                if(l.getId() == id)
                    l = object;
    }

    @Override
    public void delete(Limpeza object) {
            limpezas.remove(object);
    }

    @Override
    public void deleteById(int id) {
            var set = limpezas.stream()
                    .filter(l -> l.getId() == id)
                    .findFirst();
            set.ifPresent(l -> l.setDeleted(true));
    }

    @Override
    public List<Limpeza> listar() {
        return limpezas.stream()
                .filter(l -> l.isDeleted())
                .toList();
    }

    @Override
    public List<Limpeza> listarTodos() {
        return limpezas;
    }
}
