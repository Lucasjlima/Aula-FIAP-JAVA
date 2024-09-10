//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        var produto1 = new Produto("Coca-Cola", 5.0, "Refrigente de cola", 10);
        var produto2 = new Produto("Guaraná", 4.0, "Refrigerante de guarana", 5);


        produto1.AdicionarEstoque(5);
        System.out.println(produto1);

        }

}