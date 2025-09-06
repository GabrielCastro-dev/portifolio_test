package portifolioTest.portofolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.enums.RiscoProjeto;
import portifolioTest.portofolio.enums.StatusProjeto;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByStatusAtual(StatusProjeto statusDesejado);

    List<Projeto> findByRisco(RiscoProjeto riscoDesejado);
}
