package portifolioTest.portofolio.dto;

import lombok.Getter;
import lombok.Setter;
import portifolioTest.portofolio.enums.PapelMembro;

@Getter
@Setter
public class CreateMembroDTO {
    private String nome;
    private String email;
    private PapelMembro papel;
}
