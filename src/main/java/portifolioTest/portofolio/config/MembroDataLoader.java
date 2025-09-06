package portifolioTest.portofolio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import portifolioTest.portofolio.dto.MembroDTO;
import portifolioTest.portofolio.service.MembroService;

@Configuration
public class MembroDataLoader {

    @Bean
    CommandLineRunner initMembros(MembroService membroService) {
        return args -> {
            membroService.createMembro(new MembroDTO(null, "Ana Silva", "ana.silva@empresa.com", "funcionário"));
            membroService.createMembro(new MembroDTO(null, "Carlos Oliveira", "carlos.oliveira@empresa.com", "funcionário"));
            membroService.createMembro(new MembroDTO(null, "Mariana Souza", "mariana.souza@empresa.com", "funcionário"));
            membroService.createMembro(new MembroDTO(null, "João Pereira", "joao.pereira@empresa.com", "funcionário"));
            membroService.createMembro(new MembroDTO(null, "Fernanda Lima", "fernanda.lima@empresa.com", "funcionário"));
            membroService.createMembro(new MembroDTO(null, "Lucas Almeida", "lucas.almeida@empresa.com", "funcionário"));
            membroService.createMembro(new MembroDTO(null, "Patrícia Gomes", "patricia.gomes@empresa.com", "funcionário"));
            membroService.createMembro(new MembroDTO(null, "Rafael Costa", "rafael.costa@empresa.com", "funcionário"));
            membroService.createMembro(new MembroDTO(null, "Roberto Fernandes", "roberto.fernandes@empresa.com", "gerente"));
            membroService.createMembro(new MembroDTO(null, "Juliana Martins", "juliana.martins@empresa.com", "gerente"));
        };
    }
}
