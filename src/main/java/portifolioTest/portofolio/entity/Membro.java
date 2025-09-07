package portifolioTest.portofolio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import portifolioTest.portofolio.dto.CreateMembroDTO;
import portifolioTest.portofolio.enums.PapelMembro;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "membros")
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PapelMembro papel;

    @OneToMany(mappedBy = "membro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjetoMembro> projetos = new HashSet<>();

    public Membro(CreateMembroDTO dto){
        BeanUtils.copyProperties(dto, this);
    }

    public Membro(Long id, String nome, String email, PapelMembro papel){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.papel = papel;
    }
}
