package portifolioTest.portofolio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import portifolioTest.portofolio.entity.Membro;
import portifolioTest.portofolio.entity.PapelMembro;
import portifolioTest.portofolio.service.MembroService;

@Configuration
public class MembroDataLoader {

    @Bean
    CommandLineRunner initMembros(MembroService membroService) {
        return args -> {
            membroService.postMembro(new Membro(null, "Ana Silva", "ana.silva@empresa.com", PapelMembro.FUNCIONARIO));
            membroService.postMembro(new Membro(null, "Carlos Oliveira", "carlos.oliveira@empresa.com", PapelMembro.FUNCIONARIO));
            membroService.postMembro(new Membro(null, "Mariana Souza", "mariana.souza@empresa.com", PapelMembro.FUNCIONARIO));
            membroService.postMembro(new Membro(null, "João Pereira", "joao.pereira@empresa.com", PapelMembro.FUNCIONARIO));
            membroService.postMembro(new Membro(null, "Fernanda Lima", "fernanda.lima@empresa.com", PapelMembro.GERENTE));
            membroService.postMembro(new Membro(null, "Lucas Almeida", "lucas.almeida@empresa.com", PapelMembro.FUNCIONARIO));
            membroService.postMembro(new Membro(null, "Patrícia Gomes", "patricia.gomes@empresa.com", PapelMembro.FUNCIONARIO));
            membroService.postMembro(new Membro(null, "Rafael Costa", "rafael.costa@empresa.com", PapelMembro.FUNCIONARIO));
            membroService.postMembro(new Membro(null, "Roberto Fernandes", "roberto.fernandes@empresa.com", PapelMembro.GERENTE));
            membroService.postMembro(new Membro(null, "Juliana Martins", "juliana.martins@empresa.com", PapelMembro.FUNCIONARIO));
        };
    }
}
