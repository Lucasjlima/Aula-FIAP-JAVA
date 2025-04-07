import java.util.ArrayList;
import java.util.Objects;

public class Produto {
    //atributos
    private String nome;
    private double preco;
    private String descricao;
    private int quantidade;
    private ArrayList<caraceristicas> caraceristicas = new ArrayList<>();

    //Construtor vazio
    public Produto() {
    }

    //Construtor Intermediário
    public Produto(String nome, double preco, String descricao, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    //Construtor completo


    public Produto(String nome, double preco, String descricao, int quantidade, ArrayList<caraceristicas> caraceristicas) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.caraceristicas = caraceristicas;
    }

    //Getters e Setters
        public String getNome() {
        return nome;
        }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
        }

    public ArrayList<caraceristicas> getCaraceristicas() {
        return caraceristicas;
    }

    public void setCaraceristicas(ArrayList<caraceristicas> caraceristicas) {
        this.caraceristicas = caraceristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //Metodos ou funcoes
    public void AdicionarEstoque(int quantidade){
        this.quantidade += quantidade;
    }

    public void RemoverEstoque(int quantidade){
        if(this.quantidade - quantidade <0)
            System.out.println("Quantidade insuficiente");

        else
            this.quantidade -= quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(getPreco(), produto.getPreco()) == 0 && quantidade == produto.quantidade && Objects.equals(getNome(), produto.getNome()) && Objects.equals(getDescricao(), produto.getDescricao()) && Objects.equals(getCaraceristicas(), produto.getCaraceristicas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getPreco(), getDescricao(), quantidade, getCaraceristicas());
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", caraceristicas=" + caraceristicas +
                '}';
    }
}

