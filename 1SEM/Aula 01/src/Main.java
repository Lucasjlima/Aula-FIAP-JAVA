import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Variaveis();
        //Operadores();
        var calculo = 1 + 2 - (3 * 2) / 5.0;
        System.out.println(calculo);
        calculo++;
        System.out.println(calculo);
        calculo--;
        System.out.println(calculo);


        //condicionais();
        var scanner = new Scanner(System.in);
        System.out.println("Digite a idade");
        var idade = scanner.nextInt();
        if (idade >= 18 && idade <= 200)
            System.out.println("A pessoa é maior de idade");
        else if (idade > 200)
            System.out.println("A pessoa é um vampiro");
        else

            System.out.println("A pessoa é menor de idade");

    }
        public static void variaveis () {


            var scan = new Scanner(System.in);
            System.out.println("Digite o numero 1");
            var numero1 = scan.nextInt();
            scan.nextLine();
            System.out.println("Digite o numero 2");
            var numero2 = scan.nextInt();
            scan.nextLine();

            System.out.println(numero1 + 2);
            String nome = "FIAP";
            System.out.println(nome);

            int numero = 10;
            System.out.println(numero + 10);

            double valor = 10.5;
            System.out.println(numero + 10.5);
            short valorPequeno = 10;
            char letra = 'A';
            boolean condição = true;


            //declaração e inicialização
            var idade2 = 10;
        }

}
