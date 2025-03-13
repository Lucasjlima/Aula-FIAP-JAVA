package ccr.tdspa.repositories;

import ccr.tdspa.entities.TecnicoManutencao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TecnicoManutencaoRepository implements CrudRepository<TecnicoManutencao>{

    List<TecnicoManutencao> manutencao = new ArrayList<>();

    @Override
    public void create(TecnicoManutencao object) {
        manutencao.add(object);
    }

    @Override
    public void update(int id, TecnicoManutencao object) {
        for(TecnicoManutencao m : manutencao)
            if(m.getId() == id)
                m = object;
    }

    @Override
    public void delete(TecnicoManutencao object) {
        manutencao.remove(object);
    }

    @Override
    public void deleteById(int id) {
        var set = manutencao.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
        set.ifPresent(s-> s.setDeleted(true));
    }

    @Override
    public List<TecnicoManutencao> listar() {
        return manutencao.stream()
                .filter(m -> m.isDeleted())
                .toList();
    }

    @Override
    public Optional<TecnicoManutencao> searchById(int id) {
        return Optional.empty();
    }

    @Override
    public List<TecnicoManutencao> listarTodos() {
        return manutencao;
    }
}
