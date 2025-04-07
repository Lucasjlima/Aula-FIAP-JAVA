import java.util.Objects;

public class ProdutoDigital extends Produto{
    private String linkdowload;
    private String formatoarquivo;


    public ProdutoDigital() {
    }

    public ProdutoDigital(String linkdowload, String formatoarquivo) {
        this.linkdowload = linkdowload;
        this.formatoarquivo = formatoarquivo;
    }

    public ProdutoDigital(String nome, double preco, String descricao, int quantidade, String linkdowload, String formatoarquivo) {
        super(nome, preco, descricao, quantidade);
        this.linkdowload = linkdowload;
        this.formatoarquivo = formatoarquivo;
    }

    public String getLinkdowload() {
        return linkdowload;
    }

    public void setLinkdowload(String linkdowload) {
        this.linkdowload = linkdowload;
    }

    public String getFormatoarquivo() {
        return formatoarquivo;
    }

    public void setFormatoarquivo(String formatoarquivo) {
        this.formatoarquivo = formatoarquivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProdutoDigital that = (ProdutoDigital) o;
        return Objects.equals(getLinkdowload(), that.getLinkdowload()) && Objects.equals(getFormatoarquivo(), that.getFormatoarquivo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLinkdowload(), getFormatoarquivo());
    }

    @Override
    public String toString() {
        return "ProdutoDigital{" +
                "linkdowload='" + linkdowload + '\'' +
                ", formatoarquivo='" + formatoarquivo + '\'' +
                '}';
    }
}
