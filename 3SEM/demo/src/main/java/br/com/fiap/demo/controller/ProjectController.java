package br.com.fiap.demo.controller;

import br.com.fiap.demo.models.Project;
import br.com.fiap.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication
public class ProjectController {


    @Autowired
    private final ProjectService service;
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/projetos")
    public List<Project> listAll() {
        return service.listAll();
    }

    @GetMapping("/projetos/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id) {
        return service.findByid(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Project save(@RequestBody Project project) {
        return service.save(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(service.findByid(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
