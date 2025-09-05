package portifolioTest.portofolio.mapper;

import portifolioTest.portofolio.dto.CreateProjetoDTO;
import portifolioTest.portofolio.dto.ProjetoDTO;
import portifolioTest.portofolio.entity.Projeto;

import java.util.List;
import java.util.stream.Collectors;

public class ProjetoMapper {
    public static Projeto toEntity(CreateProjetoDTO dto) {
        return new Projeto(dto);
    }

    public static ProjetoDTO toDTO(Projeto projeto) { return new ProjetoDTO(projeto); }

    public static List<ProjetoDTO> toDTOList(List<Projeto> listaDeProjetos){
        List<ProjetoDTO> listaProjetosDTO = listaDeProjetos.stream()
                .map(ProjetoMapper::toDTO)
                .collect(Collectors.toList());

        return listaProjetosDTO;
    }
}
