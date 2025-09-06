package portifolioTest.portofolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import portifolioTest.portofolio.dto.CreateMembroDTO;
import portifolioTest.portofolio.dto.CreateProjetoDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "membros")
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PapelMembro papel;

    public Membro(CreateMembroDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
}
