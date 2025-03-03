package ccr.tdspa.entities;

import ccr.tdspa.enums.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario{
    private int id;
    private boolean deleted;
    private String nome;
    private Cargo cargo;


}
