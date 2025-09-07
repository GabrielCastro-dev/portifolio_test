package portifolioTest.portofolio.mapper;

import portifolioTest.portofolio.dto.ProjetoMembroDTO;
import portifolioTest.portofolio.entity.ProjetoMembro;

import java.util.List;
import java.util.stream.Collectors;

public class ProjetoMembroMapper {

    // Converte DTO para entidade (apenas criando referência com IDs)
    public static ProjetoMembro toEntity(ProjetoMembroDTO dto) {
        ProjetoMembro pm = new ProjetoMembro();
        if (dto.getProjetoId() != null) {
            pm.setProjeto(new portifolioTest.portofolio.entity.Projeto());
            pm.getProjeto().setId(dto.getProjetoId());
        }
        if (dto.getMembroId() != null) {
            pm.setMembro(new portifolioTest.portofolio.entity.Membro());
            pm.getMembro().setId(dto.getMembroId());
        }
        return pm;
    }

    // Converte entidade para DTO
    public static ProjetoMembroDTO toDTO(ProjetoMembro projetoMembro) {
        ProjetoMembroDTO dto = new ProjetoMembroDTO();
        if (projetoMembro.getProjeto() != null) {
            dto.setProjetoId(projetoMembro.getProjeto().getId());
        }
        if (projetoMembro.getMembro() != null) {
            dto.setMembroId(projetoMembro.getMembro().getId());
        }
        return dto;
    }

    // Converte lista de entidades para lista de DTOs
    public static List<ProjetoMembroDTO> toDTOList(List<ProjetoMembro> lista) {
        return lista.stream()
                .map(ProjetoMembroMapper::toDTO)
                .collect(Collectors.toList());
    }
}
