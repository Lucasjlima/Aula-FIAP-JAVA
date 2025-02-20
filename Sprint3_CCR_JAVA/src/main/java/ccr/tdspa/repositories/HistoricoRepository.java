package ccr.tdspa.repositories;

import ccr.tdspa.entities.Historico;

import java.util.ArrayList;
import java.util.List;

public class HistoricoRepository implements CrudRepository<Historico>{
    List<Historico> historicos = new ArrayList<>();

    @Override
    public void create(Historico object) {
        historicos.add(object);
    }

    @Override
    public void update(int id, Historico object) {
            for(Historico h: historicos)
                if(h.getId() == id)
                    h = object;
    }

    @Override
    public void delete(Historico object) {
            historicos.remove(object);
    }

    @Override
    public void deleteById(int id) {
            var set = historicos.stream()
                    .filter(h -> h.getId() == id)
                    .findFirst();
            set.ifPresent(h -> h.setDeleted(true));
    }

    @Override
    public List<Historico> listar() {
        return historicos.stream()
                .filter(h -> h.isDeleted())
                .toList();
    }

    @Override
    public List<Historico> listarTodos() {
        return historicos;
    }
}
