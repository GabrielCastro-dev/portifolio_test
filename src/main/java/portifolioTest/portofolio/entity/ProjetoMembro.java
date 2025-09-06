package portifolioTest.portofolio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "projeto_membros")
@Data
public class ProjetoMembro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @Column(name = "member_id", nullable = false)
    private Long memberId;
}
