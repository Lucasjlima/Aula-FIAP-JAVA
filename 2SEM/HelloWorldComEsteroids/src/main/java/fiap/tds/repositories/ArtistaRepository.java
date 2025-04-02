package fiap.tds.repositories;

import fiap.tds.entities.Artista;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistaRepository implements CrudRepository<Artista> {
    private List<Artista> artistas = new ArrayList<>();


    @Override
    public void adicionar(Artista object) {

    }

    @Override
    public void atualizar(int id, Artista object) {

    }

    @Override
    public void remover(Artista object) {

    }

    @Override
    public void remover(int id) {

    }

    @Override
    public void delete(Artista object) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Artista> listarTodos() {
        return List.of();
    }

    @Override
    public List<Artista> listar() {
        return List.of();
    }

    @Override
    public Optional<Artista> buscarPorId(int id) {
        return Optional.empty();
    }
}
