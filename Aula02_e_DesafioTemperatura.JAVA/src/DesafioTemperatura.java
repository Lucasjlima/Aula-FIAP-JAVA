import java.util.Scanner;

public class DesafioTemperatura {
    public static void main(String[] args) {

            var scanner = new Scanner(System.in);
        System.out.println("Insira a temperatura em graus celsius");
        int temperatura = scanner.nextInt();
        double farenheit = (int)(temperatura * 1.8) + 32;
        System.out.println("temperatura transformada em farenheit: " + farenheit);




    }
}
