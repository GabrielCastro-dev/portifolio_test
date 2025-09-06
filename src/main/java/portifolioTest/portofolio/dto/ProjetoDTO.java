package portifolioTest.portofolio.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import portifolioTest.portofolio.enums.RiscoProjeto;
import portifolioTest.portofolio.enums.StatusProjeto;
import portifolioTest.portofolio.entity.Projeto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ProjetoDTO {
    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevisto;
    private LocalDate dataTerminoEfetivo;
    private BigDecimal orcamentoTotal;
    private String descricao;
    private StatusProjeto statusAtual;
    private RiscoProjeto risco;

    public ProjetoDTO(Projeto produto){
        BeanUtils.copyProperties(produto, this);
    }
}
