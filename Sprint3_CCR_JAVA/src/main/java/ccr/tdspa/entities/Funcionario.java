package ccr.tdspa.entities;

import ccr.tdspa.enums.Cargo;
import ccr.tdspa.repositories.EventoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    private int id;
    private boolean deleted;
    private String nome;
    private Cargo cargo;


    public void resolverEvento(Funcionario funcionario, Evento evento, EventoRepository eventoRepository) {
        if (evento.isDeleted() == true) {
            System.out.println("Este evento ja foi concluido ou está sobre dominio de outro funcionario");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Funcionario: " + funcionario.getNome() + ",id: " + funcionario.getId() + "\n" +
                "Informações do reporte recebido : " + evento.getDescricao());
        System.out.println("Informe a situação do reporte: ");
        var guid = UUID.randomUUID().toString();
        var informacao = sc.nextLine();
        var caminho = "./reports/concluido/" + guid + "_eventos.txt";
        try {
            var file = new File(caminho);
            if (!file.exists()) {
                file.createNewFile();
                var writer = new FileWriter(file);
                writer.write("Id do evento: " + evento.getId() + "\n" + "Funcionário responsável: " +
                        funcionario.getNome() + "\n" + "Situacao Do Reporte:" + informacao);
                writer.close();
                System.out.println("Reporte concluido com sucesso!");
            }
            eventoRepository.deleteById(evento.getId());
        } catch (Exception e) {
            System.out.println("Erro ao exportar reporte: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public void relatoriosConcluidos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo: ");
        try{
            var nomeArquivo = sc.nextLine();
            String caminho = "./reports/concluido/" + nomeArquivo;
            var file = new File(caminho);
            var reader = new BufferedReader(new FileReader(file));
            var conteudo = "";
            String linha;
            while((linha = reader.readLine()) != null) {
                conteudo += linha + "\n";

            }
            System.out.println(conteudo);
            reader.close();
        }
        catch (Exception e){
            System.out.println("Erro ao importar arquivo: " + e.getMessage());
        }
    }

    public void importarJsos(){

    }


}
