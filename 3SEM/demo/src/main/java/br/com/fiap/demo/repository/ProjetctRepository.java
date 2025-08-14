package br.com.fiap.demo.repository;

import br.com.fiap.demo.models.Project;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProjetctRepository {
    Map<Long, Project> database = new HashMap<>();

    public List<Project> findALl(){
        return new ArrayList<>(database.values());
    }

    public Optional<Project> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public Project save (Project project) {
        database.put(project.id(), project);
        return project;
    }

    public void delete (Long id) {
        database.remove(id);
    }

}
