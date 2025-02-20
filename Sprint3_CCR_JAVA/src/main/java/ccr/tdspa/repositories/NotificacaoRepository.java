package ccr.tdspa.repositories;

import ccr.tdspa.entities.Notificacao;

import java.util.ArrayList;
import java.util.List;

public class NotificacaoRepository implements CrudRepository<Notificacao>{
    List<Notificacao> notificacoes = new ArrayList<>();

    @Override
    public void create(Notificacao object) {
        notificacoes.add(object);
    }

    @Override
    public void update(int id, Notificacao object) {
            for(Notificacao n: notificacoes)
                if(n.getId() == id)
                    n = object;
    }

    @Override
    public void delete(Notificacao object) {
            notificacoes.remove(object);
    }

    @Override
    public void deleteById(int id) {
        var set = notificacoes.stream()
                .filter(n -> n.getId() == id)
                .findFirst();
        set.ifPresent(n -> n.setDeleted(true));
    }

    @Override
    public List<Notificacao> listar() {
        return notificacoes.stream()
                .filter(n -> n.isDeleted())
                .toList();
    }

    @Override
    public List<Notificacao> listarTodos() {
        return notificacoes;
    }
}
