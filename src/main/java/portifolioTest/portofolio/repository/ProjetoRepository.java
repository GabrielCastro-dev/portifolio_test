package portifolioTest.portofolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.entity.RiscoProjeto;
import portifolioTest.portofolio.entity.StatusProjeto;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByStatusAtual(StatusProjeto statusDesejado);

    List<Projeto> findByRisco(RiscoProjeto riscoDesejado);
}
