package fiap.tds.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class _BaseEntity {
    private int id;
    private boolean deleted;
    private LocalDateTime dataCriacao = LocalDateTime.now();
}
