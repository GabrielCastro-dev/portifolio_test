package portifolioTest.portofolio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projeto_membros",
        uniqueConstraints = @UniqueConstraint(columnNames = {"projeto_id", "membro_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoMembro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "membro_id", nullable = false)
    private Membro membro;
}
