package portifolioTest.portofolio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import portifolioTest.portofolio.dto.CreateProjetoDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@Table(name = "projetos")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataTerminoPrevisto;

    private LocalDate dataTerminoEfetivo;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal orcamentoTotal;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProjeto statusAtual;

    @Enumerated(EnumType.STRING)
    private RiscoProjeto risco;

    public Projeto() {}

    public Projeto(CreateProjetoDTO dto){
        BeanUtils.copyProperties(dto, this);
    }

    public void calcularRisco() {
        if (dataInicio != null && dataTerminoPrevisto != null && orcamentoTotal != null) {
            long meses = ChronoUnit.MONTHS.between(dataInicio, dataTerminoPrevisto) + 1;

            if (orcamentoTotal.compareTo(new BigDecimal("100000")) <= 0 && meses <= 3) {
                risco = RiscoProjeto.BAIXO;
            } else if ((orcamentoTotal.compareTo(new BigDecimal("100001")) >= 0 && orcamentoTotal.compareTo(new BigDecimal("500000")) <= 0)
                    || (meses > 3 && meses <= 6)) {
                risco = RiscoProjeto.MEDIO;
            } else {
                risco = RiscoProjeto.ALTO;
            }
        }
    }
}
