package portifolioTest.portofolio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.enums.StatusProjeto;
import portifolioTest.portofolio.service.ProjetoService;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class ProjetoDataLoader {

    @Bean
    CommandLineRunner initDatabase(ProjetoService projetoService) {
        return args -> {

            Projeto p1 = new Projeto();
            p1.setNome("Sistema de Gestão");
            p1.setDataInicio(LocalDate.of(2025, 1, 1));
            p1.setDataTerminoPrevisto(LocalDate.of(2025, 6, 1));
            p1.setOrcamentoTotal(new BigDecimal("200000"));
            p1.setDescricao("Sistema para controle interno");
            p1.setStatusAtual(StatusProjeto.EM_ANDAMENTO);
            projetoService.postProjeto(p1);

            Projeto p2 = new Projeto();
            p2.setNome("Aplicativo Mobile");
            p2.setDataInicio(LocalDate.of(2025, 2, 15));
            p2.setDataTerminoPrevisto(LocalDate.of(2025, 5, 15));
            p2.setOrcamentoTotal(new BigDecimal("80000"));
            p2.setDescricao("Aplicativo para interação com clientes");
            p2.setStatusAtual(StatusProjeto.EM_ANALISE);
            projetoService.postProjeto(p2);

            Projeto p3 = new Projeto();
            p3.setNome("Plataforma E-commerce");
            p3.setDataInicio(LocalDate.of(2024, 11, 1));
            p3.setDataTerminoPrevisto(LocalDate.of(2025, 3, 30));
            p3.setOrcamentoTotal(new BigDecimal("300000"));
            p3.setDescricao("Loja virtual completa com integração a pagamentos");
            p3.setStatusAtual(StatusProjeto.EM_ANDAMENTO);
            projetoService.postProjeto(p3);

            Projeto p4 = new Projeto();
            p4.setNome("Dashboard de BI");
            p4.setDataInicio(LocalDate.of(2025, 1, 10));
            p4.setDataTerminoPrevisto(LocalDate.of(2025, 7, 20));
            p4.setOrcamentoTotal(new BigDecimal("600000"));
            p4.setDescricao("Painel de Business Intelligence para diretoria");
            p4.setStatusAtual(StatusProjeto.PLANEJADO);
            projetoService.postProjeto(p4);

            Projeto p5 = new Projeto();
            p5.setNome("Site Institucional");
            p5.setDataInicio(LocalDate.of(2025, 3, 1));
            p5.setDataTerminoPrevisto(LocalDate.of(2025, 4, 15));
            p5.setOrcamentoTotal(new BigDecimal("40000"));
            p5.setDescricao("Novo site institucional responsivo");
            p5.setStatusAtual(StatusProjeto.EM_ANALISE);
            projetoService.postProjeto(p5);

            Projeto p6 = new Projeto();
            p6.setNome("Sistema de RH");
            p6.setDataInicio(LocalDate.of(2024, 5, 1));
            p6.setDataTerminoPrevisto(LocalDate.of(2024, 10, 1));
            p6.setDataTerminoEfetivo(LocalDate.of(2024, 10, 1)); // encerrado
            p6.setDescricao("Ferramenta de gestão de recursos humanos");
            p6.setOrcamentoTotal(new BigDecimal("120000"));
            p6.setStatusAtual(StatusProjeto.ENCERRADO);
            projetoService.postProjeto(p6);

            Projeto p7 = new Projeto();
            p7.setNome("Integração ERP");
            p7.setDataInicio(LocalDate.of(2023, 7, 15));
            p7.setDataTerminoPrevisto(LocalDate.of(2023, 12, 20));
            p7.setDataTerminoEfetivo(LocalDate.of(2023, 12, 18)); // encerrado
            p7.setDescricao("Integração entre sistemas legados e novo ERP");
            p7.setOrcamentoTotal(new BigDecimal("500000"));
            p7.setStatusAtual(StatusProjeto.ENCERRADO);
            projetoService.postProjeto(p7);

            Projeto p8 = new Projeto();
            p8.setNome("Chatbot de Suporte");
            p8.setDataInicio(LocalDate.of(2025, 4, 1));
            p8.setDataTerminoPrevisto(LocalDate.of(2025, 9, 1));
            p8.setDescricao("Chatbot para atendimento ao cliente 24/7");
            p8.setOrcamentoTotal(new BigDecimal("90000"));
            p8.setStatusAtual(StatusProjeto.INICIADO);
            projetoService.postProjeto(p8);

            Projeto p9 = new Projeto();
            p9.setNome("Sistema de Logística");
            p9.setDataInicio(LocalDate.of(2024, 1, 15));
            p9.setDataTerminoPrevisto(LocalDate.of(2024, 11, 30));
            p9.setDataTerminoEfetivo(LocalDate.of(2024, 11, 28)); // encerrado
            p9.setDescricao("Otimização de rotas de entrega");
            p9.setOrcamentoTotal(new BigDecimal("250000"));
            p9.setStatusAtual(StatusProjeto.ENCERRADO);
            projetoService.postProjeto(p9);

            Projeto p10 = new Projeto();
            p10.setNome("Portal do Fornecedor");
            p10.setDataInicio(LocalDate.of(2025, 2, 1));
            p10.setDataTerminoPrevisto(LocalDate.of(2025, 8, 1));
            p10.setDescricao("Portal online para fornecedores acompanharem pedidos");
            p10.setOrcamentoTotal(new BigDecimal("150000"));
            p10.setStatusAtual(StatusProjeto.EM_ANDAMENTO);
            projetoService.postProjeto(p10);

            Projeto p11 = new Projeto();
            p11.setNome("Sistema de Segurança IoT");
            p11.setDataInicio(LocalDate.of(2024, 3, 10));
            p11.setDataTerminoPrevisto(LocalDate.of(2024, 9, 10));
            p11.setDataTerminoEfetivo(LocalDate.of(2024, 9, 12)); // encerrado
            p11.setDescricao("Monitoramento de dispositivos conectados");
            p11.setOrcamentoTotal(new BigDecimal("350000"));
            p11.setStatusAtual(StatusProjeto.ENCERRADO);
            projetoService.postProjeto(p11);

            Projeto p12 = new Projeto();
            p12.setNome("Campanha Marketing Digital");
            p12.setDataInicio(LocalDate.of(2025, 5, 5));
            p12.setDataTerminoPrevisto(LocalDate.of(2025, 7, 30));
            p12.setOrcamentoTotal(new BigDecimal("60000"));
            p12.setDescricao("Campanha de mídia paga e inbound marketing");
            p12.setStatusAtual(StatusProjeto.PLANEJADO);
            projetoService.postProjeto(p12);

            Projeto p13 = new Projeto();
            p13.setNome("Reestruturação de Rede");
            p13.setDataInicio(LocalDate.of(2023, 6, 1));
            p13.setDataTerminoPrevisto(LocalDate.of(2023, 9, 1));
            p13.setDataTerminoEfetivo(LocalDate.of(2023, 8, 30)); // encerrado
            p13.setDescricao("Atualização de infraestrutura de rede corporativa");
            p13.setOrcamentoTotal(new BigDecimal("200000"));
            p13.setStatusAtual(StatusProjeto.ENCERRADO);
            projetoService.postProjeto(p13);

            Projeto p14 = new Projeto();
            p14.setNome("Ferramenta de Analytics");
            p14.setDataInicio(LocalDate.of(2025, 6, 15));
            p14.setDataTerminoPrevisto(LocalDate.of(2026, 1, 15));
            p14.setDescricao("Plataforma de análise preditiva de dados");
            p14.setOrcamentoTotal(new BigDecimal("750000"));
            p14.setStatusAtual(StatusProjeto.EM_ANALISE);
            projetoService.postProjeto(p14);

            Projeto p15 = new Projeto();
            p15.setNome("Sistema de Pagamentos");
            p15.setDataInicio(LocalDate.of(2024, 8, 1));
            p15.setDataTerminoPrevisto(LocalDate.of(2025, 2, 1));
            p15.setDescricao("Solução integrada para processamento de pagamentos");
            p15.setOrcamentoTotal(new BigDecimal("450000"));
            p15.setStatusAtual(StatusProjeto.CANCELADO);
            projetoService.postProjeto(p15);
        };
    }
}
