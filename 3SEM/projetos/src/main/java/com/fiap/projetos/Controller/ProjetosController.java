package com.fiap.projetos.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/projetos")
public class ProjetosController {


    @GetMapping
    public List<Projeto> listar() {
        List<Projeto> lista = new ArrayList<>();
        lista.add(new Projeto(1, "Lucas", "Coordenador Lucas José"));
        lista.add(new Projeto(2, "Maria", "Coordenador Maria Clara"));
        return lista;
    }

    public record Projeto(
            int id,
            String nome,
            String responsavel


    ) {}


}
