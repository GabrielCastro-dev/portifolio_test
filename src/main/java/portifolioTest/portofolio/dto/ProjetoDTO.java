package portifolioTest.portofolio.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import portifolioTest.portofolio.entity.RiscoProjeto;
import portifolioTest.portofolio.entity.StatusProjeto;
import portifolioTest.portofolio.entity.Projeto;

import java.math.BigDecimal;
import java.time.LocalDate;



@Getter
@Setter
public class ProjetoDTO {
    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevisto;
    private LocalDate dataTerminoEfetivo;
    private BigDecimal orcamentoTotal;
    private String descricao;
    private StatusProjeto statusAtual;

    public ProjetoDTO(Projeto produto){
        BeanUtils.copyProperties(produto, this);
    }

    public ProjetoDTO(){

    }
}
