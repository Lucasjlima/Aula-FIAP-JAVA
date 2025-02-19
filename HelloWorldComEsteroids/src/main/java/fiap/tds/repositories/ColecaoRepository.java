package fiap.tds.repositories;

import fiap.tds.entities.Colecao;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ColecaoRepository
        implements CrudRepository<Colecao> {
    List<Colecao> colecoes = new ArrayList<>();

    @Override
    public void adicionar(Colecao object) {
        colecoes.add(object);
    }

    @Override
    public void atualizar(int id, Colecao object) {
        for (Colecao c: colecoes)
            if (c.getId() == id)
                c = object;
    }

    @Override
    public void remover(Colecao object) {
        colecoes.remove(object);
    }

    @Override
    public void remover(int id) {
        colecoes.removeIf(c -> c.getId() == id);
    }

    @Override
    public void delete(Colecao object) {
        object.setDeleted(true);
    }

    @Override
    public void deleteById(int id) {
            var set = colecoes.stream()
                    .filter(s -> s.getId() == id)
                    .findFirst();
            set.ifPresent(s -> s.setDeleted(true));

    }

    @Override
    public List<Colecao> listarTodos() {
        return colecoes;
    }

    @Override
    public List<Colecao> listar() {
        return colecoes.stream()
                .filter(s -> s.isDeleted())
                .toList();
    }

    public void exportar(){
        // fazer um teste de exportacao de uma string simples
        var guid = UUID.randomUUID().toString();
        var conteudo = "Esse texto será o conteudo que sera exportado para o meu arquivo";
        var caminho = "./reports/"+
                guid
                + "_colecoes.txt";
        try{
            var file = new File(caminho);
            if(file.exists())
                file.createNewFile();
            var writer = new FileWriter(file);
            writer.write(conteudo);
            writer.close();
            System.out.println("Arquivo exportado com sucesso");
        }
        catch (Exception e){
            System.out.println("Erro ao exportar o arquivo");
            throw new RuntimeException(e);
        }
    }
}
