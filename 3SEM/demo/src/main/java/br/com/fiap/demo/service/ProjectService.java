package br.com.fiap.demo.service;

import br.com.fiap.demo.models.Project;
import br.com.fiap.demo.repository.ProjetctRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class ProjectService {

    private ProjetctRepository projectRepository;

    public ProjectService(ProjetctRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> listAll(){
        return projectRepository.findALl();
    }

    public Optional<Project> findByid(Long id){
        return projectRepository.findById(id);
    }

    public Project save (Project project){
        return projectRepository.save(project);
    }

    public void delete(long id) {
        projectRepository.delete(id);
    }
}
