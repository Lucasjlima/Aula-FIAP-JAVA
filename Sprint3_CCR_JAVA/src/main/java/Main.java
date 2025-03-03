import ccr.tdspa.entities.Funcionario;
import ccr.tdspa.entities.Seguranca;
import ccr.tdspa.entities.TecnicoManutencao;
import ccr.tdspa.enums.Cargo;
import ccr.tdspa.repositories.FuncionarioRepository;

public class Main {
    public static void main(String[] args) {
        var repo = new FuncionarioRepository();
        System.out.println(repo.listarTodos());
    }
}
