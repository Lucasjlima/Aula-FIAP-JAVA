package ccr.tdspa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento{
    private int id;
    private boolean deleted;
    private String descricao;




}
