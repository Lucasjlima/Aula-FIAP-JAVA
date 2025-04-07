import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Treino {
    public static void main(String[] args) {
        ArrayList<Integer> inteiros = new ArrayList<>();

        inteiros.add(1);
        inteiros.add(2);
        inteiros.add(3);
        inteiros.add(4);
        inteiros.add(5);
        inteiros.add(6);

        System.out.println(inteiros);
        System.out.println("Separando por pares");

        inteiros.stream()
                .filter(inteiro -> inteiro % 2 == 0)
                .forEach(System.out::println);


        ArrayList<Integer> inteiros2 = new ArrayList<>();
        inteiros2.add(1);
        inteiros2.add(2);
        inteiros2.add(3);


        System.out.println("Dobrando os valores da lista");  // O map foi usado aqui, pois a lista foi transformada (Seus elementos foram alterados) pelo que eu entendi o map é usado para percorrer os itens da lista, trasnformado-os em um novo valor(Uma nova lista).(Mapear elementos de uma coleacao para um novo conjunto de valores usando o método map()).
        inteiros2.stream()
                .map(inteiro -> inteiro * 2)
                .collect(Collectors.toList())
                .forEach(System.out::println);


        ArrayList<Integer> inteiros3 = new ArrayList<>();
        inteiros3.add(10);
        inteiros3.add(20);
        inteiros3.add(30);

        var soma = inteiros3.stream()
                .reduce(0, (x, y) -> x + y);

        System.out.println( "Resultado 1: "+ soma);


        //outro jeito de fazer a mesma coisa
        // Eu preciso criar uma variavel pois os valores da lista vão se alterar, ou melhor, os novos valores precisam ser guardados  dentro da variavel, para que depois eu printe eles na tela
        var soma2 = inteiros3.stream()
                .reduce(0,Integer::sum);


        System.out.println("Resultado 2: "+ soma2);

        System.out.println("\n");

        List<String> nomes = new ArrayList<>();

        nomes.add("Pedro");
        nomes.add("Maria");
        nomes.add("Ana");
        nomes.add("Antonio");


        //Esse código inteiro aqui de baixo serve para pegar o primeiro nome da lista que contem mais de 4 caracteres usando o findFirst(). E tambem precisa ser guardado em uma variavel.
        var primeiroNomes = nomes.stream()
                        .filter(n -> n.length() > 4)
                        .findFirst();

        if (primeiroNomes.isPresent()) {
            System.out.println("Nome: "+ primeiroNomes.get());
        }
        else{
            System.out.println("Nenhum nome na lista contem mais que 4 caracteres");
        }

        System.out.println("\n");

        //Diferente do resultado do .filter() o .map() retorna os valores da lista porem como true or false
        nomes.stream()
                .map(n -> n.length() > 4)
                .forEach(f -> System.out.println( "Nomes: "+ f));


        System.out.println("\n");

        System.out.println("Nomes com mais de 4 caracteres");
        nomes.stream()
                .filter(n -> n.length() > 4)
                .forEach(resultado -> System.out.println(resultado));

        System.out.println("\n");

        List<String> frutas = new ArrayList<>();

        frutas.add("banana");
        frutas.add("maça");
        frutas.add("abacaxi");

        frutas.stream()
                .sorted()
                .collect(Collectors.toList())
                .forEach(System.out::println);


        System.out.println("Parte dois do desafio!");

        System.out.println("\n");

        ArrayList<Integer> inteiros4 = new ArrayList<>();
        inteiros4.add(10);
        inteiros4.add(22);
        inteiros4.add(5);
        inteiros4.add(75);
        inteiros4.add(45);



        //Este é um dos jeitos de fazer esse exercicio, primeiro eu coloco o .max passo o tipo da lista que é a Integer e depois o compare para ele percorrer os dados da lista e compara-los. O .ifPresent é usado de forma bem clara "Se esse tal argumento estiver presente imprima-o.".
        inteiros4.stream()
                .max(Integer::compare)
                .ifPresent(System.out::println);

        System.out.println("Segundo jeito");
        // É um jeito mais longo de se fazer porem utiliza de mais boas praticas dentro do seu codigo.
        Optional<Integer> maiorNumero = inteiros4.stream()
                .max(Integer::compareTo);

        maiorNumero.ifPresent(System.out::println);

        System.out.println("\n");

        List<String> names = Arrays.asList("Ana","Mariana", "José", "Roberto", "Carlos");
        //Novamente eu preciso armazernar o numero que vai sair na logica do programa para que depois eu exiba ele no printe.
        //Não sei outro jeito de realizar esse exercicio sem ter que criar uma variavel.
        var namesMaior5 = names.stream()
                .filter(n -> n.length() > 5)
                .toList()
                .stream().count();

        System.out.println(namesMaior5);

        // Aprendi um novo jeito de criar listas usando o List.of
        List<String> names2 = List.of("João", "Maria", "Pedro");
        //Também não sei como faria pra resolver isso sem criar um var, na minha lógica como o valor original da lista vai mudar, eu preciso armazenar esse novo valor em uma variavel.
        //Ainda não entendo muito bem sobre esse joining.
        var listaSemVirgulas = names2.stream()
                .collect(Collectors.joining(","));

        System.out.println(listaSemVirgulas);

        System.out.println("\n");

        //Esse é um segundo jeito de fazer usando funções diferentes. e tambem nao se usa stream api nesse.
        System.out.println("---Jeito diferente---:  ");
        var listaSemVirgulas2 = String.join(",", names2);
        System.out.println(listaSemVirgulas2);

        System.out.println("\n");

        List<Integer> negativos = Arrays.asList(-1, 2, -3, 4, -5);

        negativos.stream()
                .filter(n -> n < 0 )
                .map(n -> n * -1)
                .toList()
                .forEach(System.out::println);


        System.out.println("\n");

        //Crio uma variavel para guardar a nova lista, uso o sorted para ordenar, ele aceita uma funcao chamada Comparator.comparing e dai eu passo o lambda dizendo que quero que compare o tamanho(.legth), ai eu ordeno eles em uma nova lista.
        List<String> nomes5 = List.of("João", "Ana", "Roberto", "Maria");
                var ordenados = nomes5.stream()
                        .sorted(Comparator.comparing(nome -> nome.length()))
                        .toList();
        System.out.println(ordenados);





    }

}
