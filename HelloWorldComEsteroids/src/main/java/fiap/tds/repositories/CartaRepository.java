package fiap.tds.repositories;

import fiap.tds.entities.Carta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartaRepository implements CrudRepository<Carta> {
    private List<Carta> cartas = new ArrayList<>();


    @Override
    public void adicionar(Carta object) {
        cartas.add(object);
    }

    @Override
    public void atualizar(int id, Carta object) {
        for (Carta c: cartas)
            if (c.getId() == id)
                c = object;
    }

    @Override
    public void remover(Carta object) {
        cartas.remove(object);
    }

    @Override
    public void remover(int id) {
        cartas.removeIf(c -> c.getId() == id);
    }

    @Override
    public void delete(Carta object) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Carta> listarTodos() {
        return List.of();
    }

    @Override
    public List<Carta> listar() {
        return cartas;
    }

    @Override
    public Optional<Carta> buscarPorId(int id) {
        return Optional.empty();
    }
}
