package portifolioTest.portofolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumoDTO {
    private Map<String, Long> quantidadeProjetosPorStatus;
    private Map<String, BigDecimal> totalOrcadoPorStatus;
    private Double mediaDuracaoProjetosEncerrados;
    private Long totalMembrosUnicos;
}
