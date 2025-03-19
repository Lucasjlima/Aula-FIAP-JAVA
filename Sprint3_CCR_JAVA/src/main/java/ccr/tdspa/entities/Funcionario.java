package ccr.tdspa.entities;

import ccr.tdspa.enums.Cargo;
import ccr.tdspa.repositories.EventoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static final String SENHA_ADMIN = "CCR2025";
    private int id;
    private boolean deleted;
    private String nome;
    private Cargo cargo;


    public void resolverEvento(Funcionario funcionario, Evento evento) {
        if (evento.isDeleted()) {
            System.out.println("Este evento já foi concluído ou está sob domínio de outro funcionário");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Funcionário: " + funcionario.getNome() + ", ID: " + funcionario.getId() + "\n" +
                "Informações do reporte recebido: " + evento.getDescricao());

        System.out.println("Informe a situação do reporte: ");
        var guid = UUID.randomUUID().toString();
        var informacao = sc.nextLine();


        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dataHoraAtual.format(formatter);

        var caminho = "./reports/concluido/" + guid + "_eventos.txt";

        try {
            var file = new File(caminho);
            if (!file.exists()) {
                file.createNewFile();
                var writer = new FileWriter(file);


                writer.write("Id do evento: " + evento.getId() + "\n" +
                        "Funcionário responsável: " + funcionario.getNome() + "\n" +
                        "Situação do reporte: " + informacao + "\n" +
                        "Data e Hora da Conclusão:: " + dataFormatada);

                writer.close();
                System.out.println("Reporte concluído com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao exportar reporte: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public void relatoriosConcluidos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("DIGITE A SENHA DE ADMIN PARA TER ACESSO: ");
        var senha = sc.nextLine();
        if (senha.equals(SENHA_ADMIN)) {
            System.out.println("Digite o nome do arquivo: ");
            try {
                var nomeArquivo = sc.nextLine();
                String caminho = "./reports/concluido/" + nomeArquivo;
                var file = new File(caminho);
                var reader = new BufferedReader(new FileReader(file));
                var conteudo = "";
                String linha;
                while ((linha = reader.readLine()) != null) {
                    conteudo += linha + "\n";

                }
                System.out.println(conteudo);
                reader.close();
            } catch (Exception e) {
                System.out.println("Erro ao importar arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Acesso negado!");
            System.out.println("Encerrando programa...");
        }
    }

    public void menuFunc(Funcionario funcionario, Evento evento) {
        var sc = new Scanner(System.in);
        label:
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Digite a opção desejada: ");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("1 - Resolver Evento");
            System.out.println("2 - Importar relatórios concluídos");
            System.out.println("3 - Sair do sistema");

            var opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    resolverEvento(funcionario, evento);
                    break;
                case 2:
                    relatoriosConcluidos();
                    break;
                case 3:
                    System.out.println("Sistema finalizando...");
                    break label;
            }


        }
    }

}
