package portifolioTest.portofolio.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import portifolioTest.portofolio.enums.StatusProjeto;
import portifolioTest.portofolio.entity.Projeto;

import java.math.BigDecimal;
import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
public class CreateProjetoDTO {
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevisto;
    private LocalDate dataTerminoEfetivo;
    private BigDecimal orcamentoTotal;
    private String descricao;
    private StatusProjeto statusAtual;

    public CreateProjetoDTO(Projeto produto){
        BeanUtils.copyProperties(produto, this);
    }
}
