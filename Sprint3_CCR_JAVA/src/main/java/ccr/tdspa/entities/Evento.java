package ccr.tdspa.entities;

import ccr.tdspa.enums.DescricaoEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
    private int id;
    private boolean deleted;
    private DescricaoEvento descricao;


}
