package portifolioTest.portofolio.service;

import org.springframework.stereotype.Service;
import portifolioTest.portofolio.dto.ResumoDTO;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.entity.ProjetoMembro;
import portifolioTest.portofolio.enums.StatusProjeto;
import portifolioTest.portofolio.repository.ProjetoRepository;
import portifolioTest.portofolio.repository.ProjetoMembroRepository;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumoService {

    private final ProjetoRepository projetoRepository;
    private final ProjetoMembroRepository projetoMembroRepository;

    public ResumoService(ProjetoRepository projetoRepository, ProjetoMembroRepository projetoMembroRepository) {
        this.projetoRepository = projetoRepository;
        this.projetoMembroRepository = projetoMembroRepository;
    }

    public ResumoDTO gerarResumo() {
        List<Projeto> projetos = projetoRepository.findAll();
        List<ProjetoMembro> alocacoes = projetoMembroRepository.findAll();

        // 1. Quantidade de projetos por status
        Map<String, Long> qtdPorStatus = projetos.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getStatusAtual().name(),
                        Collectors.counting()
                ));

        // 2. Total orçado por status
        Map<String, BigDecimal> totalPorStatus = projetos.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getStatusAtual().name(),
                        Collectors.mapping(Projeto::getOrcamentoTotal,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));

        // 3. Média de duração dos projetos encerrados
        Double mediaDuracao = projetos.stream()
                .filter(p -> p.getStatusAtual() == StatusProjeto.ENCERRADO && p.getDataInicio() != null && p.getDataTerminoEfetivo() != null)
                .mapToLong(p -> ChronoUnit.DAYS.between(p.getDataInicio(), p.getDataTerminoEfetivo()))
                .average()
                .orElse(0.0);

        // 4. Total de membros únicos alocados
        Long membrosUnicos = alocacoes.stream()
                .map(pm -> pm.getMembro().getId())
                .distinct()
                .count();

        return new ResumoDTO(qtdPorStatus, totalPorStatus, mediaDuracao, membrosUnicos);
    }
}
