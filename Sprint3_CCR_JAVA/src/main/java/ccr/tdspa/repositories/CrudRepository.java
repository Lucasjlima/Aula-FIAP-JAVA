package ccr.tdspa.repositories;

import java.util.List;

public interface CrudRepository <T>{
    void create(T object);
    void update(int id, T object);
    void delete(T object);
    void deleteById(int id);
    List<T> listarTodos(); //Este listarTodos apenas serve para os ADMINs.
    List<T> listar();
}
