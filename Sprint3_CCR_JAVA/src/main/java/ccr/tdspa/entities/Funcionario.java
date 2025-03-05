package ccr.tdspa.entities;

import ccr.tdspa.enums.Cargo;
import ccr.tdspa.repositories.EventoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario{
    private int id;
    private boolean deleted;
    private String nome;
    private Cargo cargo;


    public void resolverEvento(Funcionario funcionario, Evento evento, EventoRepository eventoRepository) {
        if(evento.isDeleted() == true){
            System.out.println("Este evento ja foi concluido ou está sobre dominio de outro funcionario");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Funcionario: " + funcionario.getNome() + ",id: " + funcionario.getId() + "\n" +
                "Informações do reporte recebido : " + evento.getDescricao());
        System.out.println("Informe a situação do reporte: ");
        var guid = UUID.randomUUID().toString();
        var informacao = sc.nextLine();
        var caminho = "./reports/" + guid +"_eventos.txt";
        try{
            var file = new File(caminho);
            if(!file.exists()){
                file.createNewFile();
            var writer = new FileWriter(file);
            writer.write("Id do evento: " + evento.getId() + "\n"  + "Funcionário responsável: "+
                    funcionario.getNome() + "\n" + "Situacao Do Reporte:" + informacao);
            writer.close();
                System.out.println("Reporte concluido com sucesso!");
            }
            eventoRepository.deleteById(evento.getId());
        }
        catch (Exception e){
            System.out.println("Erro ao exportar reporte: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }


}
