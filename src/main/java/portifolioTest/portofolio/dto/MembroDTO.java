package portifolioTest.portofolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembroDTO {
    private Long id;
    private String nome;
    private String email;
    private String papel;
}
