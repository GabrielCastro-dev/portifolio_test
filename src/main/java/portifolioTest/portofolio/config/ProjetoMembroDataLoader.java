package portifolioTest.portofolio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import portifolioTest.portofolio.entity.Membro;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.entity.ProjetoMembro;
import portifolioTest.portofolio.service.ProjetoMembroService;

@Configuration
public class ProjetoMembroDataLoader {

    @Bean
    CommandLineRunner initProjetoMembros(ProjetoMembroService projetoMembroService) {
        return args -> {
            try {
                // Projeto 1: Sistema de Gestão
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(1L), new Membro(1L)));
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(1L), new Membro(5L))); // gerente
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(1L), new Membro(2L)));

                // Projeto 2: Aplicativo Mobile
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(2L), new Membro(3L)));
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(2L), new Membro(6L)));

                // Projeto 3: Plataforma E-commerce
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(3L), new Membro(4L)));
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(3L), new Membro(9L))); // gerente
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(3L), new Membro(7L)));

                // Projeto 4: Dashboard de BI
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(4L), new Membro(8L)));
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(4L), new Membro(10L)));

                // Projeto 5: Site Institucional
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(5L), new Membro(1L)));
                projetoMembroService.adicionarMembro(new ProjetoMembro(null, new Projeto(5L), new Membro(2L)));

            } catch (Exception e) {
                System.out.println("Erro ao carregar ProjetoMembros: " + e.getMessage());
            }
        };
    }
}
