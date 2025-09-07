package portifolioTest.portofolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import portifolioTest.portofolio.entity.ProjetoMembro;
import portifolioTest.portofolio.enums.StatusProjeto;

public interface ProjetoMembroRepository extends JpaRepository<ProjetoMembro, Long> {

    int countByProjetoId(Long projetoId);

    @Query("SELECT COUNT(pm) FROM ProjetoMembro pm " +
            "WHERE pm.membro.id = :membroId " +
            "AND pm.projeto.statusAtual NOT IN (portifolioTest.portofolio.enums.StatusProjeto.ENCERRADO, portifolioTest.portofolio.enums.StatusProjeto.CANCELADO)")
    Long countProjetosAtivosPorMembro(@Param("membroId") Long membroId);

}
