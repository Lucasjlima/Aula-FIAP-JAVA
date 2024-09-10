import javax.swing.*;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Condicionais()
        var scanner = new Scanner(System.in);
        System.out.println("Digite sua idade: ");
        var idade = scanner.nextInt();
        scanner.nextLine();

            if(idade >= 18 && idade <= 200){
                System.out.println("A pessoa é maior de idade");}
            else if(idade >= 200){
                System.out.println("A pessoa é um vampiro");
            }



    }
}