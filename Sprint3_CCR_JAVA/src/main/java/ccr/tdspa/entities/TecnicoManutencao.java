package ccr.tdspa.entities;

import ccr.tdspa.enums.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TecnicoManutencao extends Funcionario{

    public TecnicoManutencao(int id, boolean deleted, String nome, Cargo cargo) {
        super(id, deleted, nome, cargo);
    }
}
