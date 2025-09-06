package portifolioTest.portofolio.dto;

import lombok.*;
import org.springframework.beans.BeanUtils;
import portifolioTest.portofolio.entity.Membro;
import portifolioTest.portofolio.entity.PapelMembro;

@Getter
@Setter
@NoArgsConstructor
public class MembroDTO {
    private Long id;
    private String nome;
    private String email;
    private PapelMembro papel;

    public MembroDTO(Membro membro){
        BeanUtils.copyProperties(membro, this);
    }
}
