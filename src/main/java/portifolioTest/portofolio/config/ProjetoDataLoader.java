package portifolioTest.portofolio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.entity.StatusProjeto;
import portifolioTest.portofolio.repository.ProjetoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class ProjetoDataLoader {

    @Bean
    CommandLineRunner initDatabase(ProjetoRepository projetoRepository) {
        return args -> {
            Projeto p1 = new Projeto();
            p1.setNome("Sistema de Gestão");
            p1.setDataInicio(LocalDate.of(2025, 1, 1));
            p1.setDataTerminoPrevisto(LocalDate.of(2025, 6, 1));
            p1.setOrcamentoTotal(new BigDecimal("200000"));
            p1.setDescricao("Sistema para controle interno");
            p1.setStatusAtual(StatusProjeto.EM_ANDAMENTO);
            p1.calcularRisco();

            Projeto p2 = new Projeto();
            p2.setNome("Aplicativo Mobile");
            p2.setDataInicio(LocalDate.of(2025, 2, 15));
            p2.setDataTerminoPrevisto(LocalDate.of(2025, 5, 15));
            p2.setOrcamentoTotal(new BigDecimal("80000"));
            p2.setDescricao("Aplicativo para interação com clientes");
            p2.setStatusAtual(StatusProjeto.EM_ANALISE);
            p2.calcularRisco();

            Projeto p3 = new Projeto();
            p3.setNome("Plataforma E-commerce");
            p3.setDataInicio(LocalDate.of(2024, 11, 1));
            p3.setDataTerminoPrevisto(LocalDate.of(2025, 3, 30));
            p3.setOrcamentoTotal(new BigDecimal("300000"));
            p3.setDescricao("Loja virtual completa com integração a pagamentos");
            p3.setStatusAtual(StatusProjeto.EM_ANDAMENTO);
            p3.calcularRisco();

            Projeto p4 = new Projeto();
            p4.setNome("Dashboard de BI");
            p4.setDataInicio(LocalDate.of(2025, 1, 10));
            p4.setDataTerminoPrevisto(LocalDate.of(2025, 7, 20));
            p4.setOrcamentoTotal(new BigDecimal("600000"));
            p4.setDescricao("Painel de Business Intelligence para diretoria");
            p4.setStatusAtual(StatusProjeto.EM_ANDAMENTO);
            p4.calcularRisco();

            Projeto p5 = new Projeto();
            p5.setNome("Site Institucional");
            p5.setDataInicio(LocalDate.of(2025, 3, 1));
            p5.setDataTerminoPrevisto(LocalDate.of(2025, 4, 15));
            p5.setOrcamentoTotal(new BigDecimal("40000"));
            p5.setDescricao("Novo site institucional responsivo");
            p5.setStatusAtual(StatusProjeto.EM_ANALISE);
            p5.calcularRisco();

            // Salvar no banco
            projetoRepository.save(p1);
            projetoRepository.save(p2);
            projetoRepository.save(p3);
            projetoRepository.save(p4);
            projetoRepository.save(p5);
        };
    }
}
