package fiap.tds;

import fiap.tds.entities.Colecao;
import fiap.tds.repositories.ColecaoRepository;

import java.util.Scanner;

public class Main {
    private static final String SENHA_MESTRE = "SENHA123";

    public static void main(String[] args) {
        var colecaoRepository = new ColecaoRepository();

        System.out.printf("Bem vindo ao sistema de cartas");
        while (true) {
            System.out.println("Digite a opção desejada: ");
            System.out.println("1 - Adicionar coleção");
            System.out.println("2 - Listar coleções");
            System.out.println("3 - Remover coleções");
            System.out.println("4 - Listar todas as coleções (ADMIN APENAS");
            System.out.println("5 - Sair");
            var scaner = new Scanner(System.in);
            var opcao = scaner.nextInt();
            scaner.nextLine();

            if (opcao == 1)
                CadastrarColecao(colecaoRepository);
            if (opcao == 2)
                System.out.println(colecaoRepository.listar());
            if (opcao == 3)
                RemoverColecao(colecaoRepository);
            if (opcao == 4) {
                System.out.println("Digite a senha de administrador");
                var senha = scaner.nextLine();
                if (senha.equals(SENHA_MESTRE))
                    System.out.println(colecaoRepository.listarTodos());
                else
                    System.out.println("Acesso negado");
            }
            if (opcao == 5)
                break;


        }
    }

    public static void CadastrarColecao(ColecaoRepository repository) {
        var scan = new Scanner(System.in);
        System.out.println("Digite o id da coleção: ");
        var id = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite o nome da coleção: ");
        var nome = scan.nextLine();
        System.out.println("Digite o código da coleção");
        var codigo = scan.nextLine();
        System.out.println("Digite a data de lançamento da coleção");
        var dataDeLancamento = scan.nextLine();
        var colecao = new Colecao(nome, codigo, dataDeLancamento);
        colecao.setId(id);
        repository.adicionar(colecao);

    }


    public static void RemoverColecao(ColecaoRepository repository) {
        System.out.println("Digite o id da coleção que deseja remover");
        var scan = new Scanner(System.in);
        var id = scan.nextInt();
        scan.nextLine();
        repository.deleteById(id);
    }

}
