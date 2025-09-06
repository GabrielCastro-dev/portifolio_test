package portifolioTest.portofolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portifolioTest.portofolio.entity.Membro;

public interface MembroRepository extends JpaRepository<Membro, Long> {
}
