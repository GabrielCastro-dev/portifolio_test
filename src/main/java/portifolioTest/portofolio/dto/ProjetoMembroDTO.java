package portifolioTest.portofolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import portifolioTest.portofolio.entity.ProjetoMembro;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoMembroDTO {
    private Long projetoId;
    private Long membroId;

    public ProjetoMembroDTO(ProjetoMembro projetoMembro){
        BeanUtils.copyProperties(projetoMembro, this);
    }
}
