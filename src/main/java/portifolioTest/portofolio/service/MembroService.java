package portifolioTest.portofolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portifolioTest.portofolio.dto.CreateMembroDTO;
import portifolioTest.portofolio.dto.MembroDTO;
import portifolioTest.portofolio.entity.Membro;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.repository.MembroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    public List<Membro> getMembros(){
        return membroRepository.findAll();
    }

    public Membro findById(Long id) {
        Optional<Membro> membroEncontrado = membroRepository.findById(id);

        if (!membroEncontrado.isPresent()) {
            throw new RuntimeException("Membro não encontrado.");
        }

        return membroEncontrado.get();
    }

    public Membro postMembro(Membro membro) {
        return membroRepository.save(membro); // Retorna o membro como foi salvo no banco
    }

    public Membro putMembro(Long id, Membro membroAtualizado){
        Membro membroEncontrado = findById(id);

        membroEncontrado.setNome(membroAtualizado.getNome());
        membroEncontrado.setEmail(membroAtualizado.getEmail());
        membroEncontrado.setPapel(membroAtualizado.getPapel());

        membroRepository.save(membroEncontrado);
        return membroEncontrado;
    }

}
