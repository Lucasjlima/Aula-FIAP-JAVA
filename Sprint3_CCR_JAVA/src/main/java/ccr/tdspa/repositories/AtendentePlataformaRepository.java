package ccr.tdspa.repositories;

import ccr.tdspa.entities.AtendentePlataforma;

import java.util.ArrayList;
import java.util.List;

public class AtendentePlataformaRepository implements CrudRepository<AtendentePlataforma>
{
    List<AtendentePlataforma> atendentes = new ArrayList<>();

    @Override
    public void create(AtendentePlataforma object) {
            atendentes.add(object);
    }

    @Override
    public void update(int id, AtendentePlataforma object) {
            for(AtendentePlataforma a : atendentes)
                if(a.getId() == id)
                    a = object;
    }

    @Override
    public void delete(AtendentePlataforma object) {
                atendentes.remove(object);
    }

    @Override
    public void deleteById(int id) {
            var set = atendentes.stream()
                    .filter(a -> a.getId() == id)
                    .findFirst();
            set.ifPresent(s -> s.setDeleted(true));
    }

    @Override
    public List<AtendentePlataforma> listarTodos() {
        return atendentes;
    }

    @Override
    public List<AtendentePlataforma> listar() {
        return atendentes.stream()
                .filter(s -> s.isDeleted())
                .toList();
    }
}
