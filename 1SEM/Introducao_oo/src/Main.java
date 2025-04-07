import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        var produto1 = new Produto("Coca-Cola", 5.0, "Refrigente de cola", 10);

        var produto2 = new Produto("Guaraná", 4.0, "Refrigerante de guarana", 5);
        var produto3 = new Produto("Fanta Kiw" , 0 , null, 0);


        produto3.setNome("Fanta Kiwi");
        produto3.setDescricao("Refrigente de kiwi");

        var produto4 = new Produto();
        produto4.setNome("Sprite");
        produto4.AdicionarEstoque(4);



        produto1.AdicionarEstoque(5);
        System.out.println(produto1);

        var numero = 10;
        if(numero == 10)
            System.out.println("Numero igual a 10");

        var texto = "teste";
        if(texto.equals("teste"))
            System.out.println("Texto igual ao teste");

        var produto5 = new Produto();
        if(produto5.equals(produto4))
         System.out.println("Produto sao iguais");
         else
            System.out.println("Produtos nao sao iguais");


         var produtofisico1 = new ProdutoFisico();
         produtofisico1.setNome("Elden ring - Midia Fisica - PS4");
         produtofisico1.setPreco(200.0);
         produtofisico1.setDescricao(
                 "Midia fisica do jogo Elden Ring PS4");
         produtofisico1.setPreco(200.0);
         produtofisico1.setDimensao(new Dimensao(20, 20 , 20));
         produtofisico1.getCaraceristicas().add(new caraceristicas("plataforma" , "PS4" ));
         produtofisico1.getCaraceristicas().add(new caraceristicas("distribuidora " , "From - Software"));

         System.out.println(produtofisico1);


        }

}