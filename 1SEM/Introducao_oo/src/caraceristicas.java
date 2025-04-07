import java.util.Objects;

public class caraceristicas {
    private String nome;
    private String descricao;

    public caraceristicas() {
    }

    public caraceristicas(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        caraceristicas that = (caraceristicas) o;
        return Objects.equals(getNome(), that.getNome()) && Objects.equals(getDescricao(), that.getDescricao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getDescricao());
    }

    @Override
    public String toString() {
        return "caraceristicas{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
