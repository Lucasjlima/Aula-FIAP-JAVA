import lombok.*;

import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carta {
    @DataField(prompt = "Nome da carta")
    private String nome;

    @DataField(prompt = "Texto da carta")
    private String texto;

    @DataField(prompt = "Nome do artista")
    private String artista;

}
