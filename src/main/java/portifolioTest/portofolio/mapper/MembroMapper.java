package portifolioTest.portofolio.mapper;

import portifolioTest.portofolio.dto.CreateMembroDTO;
import portifolioTest.portofolio.dto.MembroDTO;
import portifolioTest.portofolio.entity.Membro;

import java.util.List;
import java.util.stream.Collectors;

public class MembroMapper {
    public static Membro toEntity(CreateMembroDTO dto) {
        return new Membro(dto);
    }

    public static MembroDTO toDTO(Membro membro){ return new MembroDTO(membro); }

    public static List<MembroDTO> toDTOList(List<Membro> listaDeMembros){
        List<MembroDTO> listaMembrosDTO = listaDeMembros.stream()
                .map(MembroMapper::toDTO)
                .collect(Collectors.toList());

        return listaMembrosDTO;
    }
}
