package portifolioTest.portofolio.service;

import org.springframework.stereotype.Service;
import portifolioTest.portofolio.dto.MembroDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembroService {

    private final List<MembroDTO> membros = new ArrayList<>();

    public MembroDTO getMembroById(Long id) {
        int index = id.intValue() - 1;
        if (index < 0 || index >= membros.size()) {
            throw new RuntimeException("Membro não encontrado");
        }
        return membros.get(index);
    }

    public MembroDTO createMembro(MembroDTO membro) {
        Long id = (long) (membros.size() + 1);
        membro.setId(id);
        membros.add(membro);
        return membro;
    }

    public List<MembroDTO> getAllMembros() {
        return membros;
    }
}
