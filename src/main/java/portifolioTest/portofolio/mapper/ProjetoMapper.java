package portifolioTest.portofolio.mapper;

import portifolioTest.portofolio.dto.ProjetoDTO;
import portifolioTest.portofolio.entity.Projeto;

public class ProjetoMapper {
    public static Projeto toEntity(ProjetoDTO dto) {
        return new Projeto(dto);
    }

    public static ProjetoDTO toDTO(Projeto projeto) {
        return new ProjetoDTO(projeto);
    }
}
