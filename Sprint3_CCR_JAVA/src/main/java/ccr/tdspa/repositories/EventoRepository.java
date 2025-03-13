package ccr.tdspa.repositories;

import ccr.tdspa.entities.Evento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventoRepository implements CrudRepository<Evento> {

    List<Evento> eventos = new ArrayList<>();
    @Override
    public void create(Evento object) {
        eventos.add(object);
    }

    @Override
    public void update(int id, Evento object) {
        for(Evento e: eventos)
            if(e.getId() == id)
                e = object;
    }

    @Override
    public void delete(Evento object) {
        eventos.remove(object);
    }

    @Override
    public void deleteById(int id) {
        var set = eventos.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        set.ifPresent(e -> e.setDeleted(true));
    }

    @Override
    public List<Evento> listarTodos() {
        return eventos;
    }

    @Override
    public List<Evento> listar() {
        return eventos.stream()
                .filter(e -> e.isDeleted())
                .toList();
    }

    @Override
    public Optional<Evento> searchById(int id) {
        return Optional.empty();
    }


}
