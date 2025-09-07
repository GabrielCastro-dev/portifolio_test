package portifolioTest.portofolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portifolioTest.portofolio.entity.ProjetoMembro;
import portifolioTest.portofolio.enums.StatusProjeto;
import portifolioTest.portofolio.repository.ProjetoMembroRepository;

import java.util.List;

@Service
public class ProjetoMembroService {

    @Autowired
    private ProjetoMembroRepository projetoMembroRepository;

    /** Lista todos os membros alocados em projetos */
    public List<ProjetoMembro> listarTodos() {
        return projetoMembroRepository.findAll();
    }

    /** Adiciona um membro a um projeto respeitando as regras de negócio */
    public ProjetoMembro adicionarMembro(ProjetoMembro projetoMembro) throws Exception {
        Long projetoId = projetoMembro.getProjeto().getId();
        Long membroId = projetoMembro.getMembro().getId();

        // Regra 1: máximo 10 membros por projeto
        int membrosAtuais = projetoMembroRepository.countByProjetoId(projetoId);
        if (membrosAtuais >= 10) {
            throw new Exception("Não é possível adicionar mais membros: limite de 10 atingido para este projeto");
        }

        // Regra 2: máximo 3 projetos ativos por membro
        List<StatusProjeto> inativos = List.of(StatusProjeto.ENCERRADO, StatusProjeto.CANCELADO);
        Long projetosAtivos = projetoMembroRepository.countProjetosAtivosPorMembro(membroId);
        if (projetosAtivos >= 3) {
            throw new Exception("Não é possível alocar este membro: já possui 3 projetos ativos");
        }

        return projetoMembroRepository.save(projetoMembro);
    }

    /** Atualiza relação de membro e projeto */
    public ProjetoMembro atualizarRelacao(Long id, ProjetoMembro atualizado) throws Exception {
        ProjetoMembro existente = this.buscarPorId(id);
        existente.setMembro(atualizado.getMembro());
        existente.setProjeto(atualizado.getProjeto());
        return projetoMembroRepository.save(existente);
    }

    /** Remove um membro de um projeto (não verifica mínimo de 1 neste método) */
    public void removerMembro(Long id) throws Exception {
        if (!projetoMembroRepository.existsById(id)) {
            throw new Exception("Membro não encontrado");
        }
        projetoMembroRepository.deleteById(id);
    }

    /** Busca um membro de um projeto por ID */
    public ProjetoMembro buscarPorId(Long id) throws Exception {
        return projetoMembroRepository.findById(id)
                .orElseThrow(() -> new Exception("Membro não encontrado"));
    }
}
