package fiap.tds.repositories;

import java.util.List;
import java.util.Optional;

// Ao usar o <T> na interface, você está
// dizendo que a interface é genérica
// e que o tipo T será definido na
// implementação
public interface CrudRepository<T> {
    void adicionar(T object);
    void atualizar(int id, T object);
    void remover(T object);
    void remover(int id);

    void delete(T object);
    void deleteById(int id);

    List<T> listarTodos();
    List<T> listar();
    Optional<T> buscarPorId(int id);
}
