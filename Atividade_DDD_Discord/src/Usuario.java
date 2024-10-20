import java.util.Objects;

public class Usuario {
    private int Id;
    private String nome;

    public Usuario() {
    }

    public Usuario(int id, String nome) {
        Id = id;
        this.nome = nome;
    }

    public int getId() {
        return Id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return getId() == usuario.getId() && Objects.equals(getNome(), usuario.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome());
    }

    public String toString(){
        return nome;
    }
}
