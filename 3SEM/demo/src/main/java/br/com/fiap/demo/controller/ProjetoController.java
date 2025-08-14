package br.com.fiap.demo.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class ProjetoController {

    @RequestMapping("/")
    public String firstPage() {
        return "Hello, World!";
    }

}
