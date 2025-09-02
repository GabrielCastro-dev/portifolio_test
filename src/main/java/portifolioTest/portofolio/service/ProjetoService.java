package portifolioTest.portofolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.entity.StatusProjeto;
import portifolioTest.portofolio.repository.ProjetoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    public String postProjeto(Projeto projeto){
        this.projetoRepository.save(projeto);
        return "Projeto salvo com sucesso.";
    }

    public List<Projeto> getProjetos(){
        return projetoRepository.findAll();
    }

    public Projeto findById(Long id) {
        Optional<Projeto> projetoEncontrado = projetoRepository.findById(id);

        if (!projetoEncontrado.isPresent()) {
            throw new RuntimeException("Projeto não encontrado.");
        }

        return projetoEncontrado.get();
    }

    public String deleteById(Long id){
        Projeto projetoADeletar = findById(id);
        projetoRepository.delete(projetoADeletar);
        return "Projeto deletado com sucesso.";
    }

    public String editById(Long id, Projeto projetoAtualizado){
        Projeto projetoExistente = findById(id);

        // Atualiza os campos
        projetoExistente.setNome(projetoAtualizado.getNome());
        projetoExistente.setDataInicio(projetoAtualizado.getDataInicio());
        projetoExistente.setDataTerminoPrevisto(projetoAtualizado.getDataTerminoPrevisto());
        projetoExistente.setDataTerminoEfetivo(projetoAtualizado.getDataTerminoEfetivo());
        projetoExistente.setOrcamentoTotal(projetoAtualizado.getOrcamentoTotal());
        projetoExistente.setDescricao(projetoAtualizado.getDescricao());
        projetoExistente.setStatusAtual(projetoAtualizado.getStatusAtual());
        projetoExistente.setRisco(projetoAtualizado.getRisco());

        // Recalcula risco se necessário
        projetoExistente.calcularRisco();

        projetoRepository.save(projetoExistente);

        return "Projeto atualizado com sucesso.";
    }

    public String cancelarProjeto(Long id){
        Projeto projetoExistente = findById(id);

        if(projetoExistente.getStatusAtual() == StatusProjeto.CANCELADO){
            throw new RuntimeException("Este projeto já foi cancelado");
        }

        projetoExistente.setStatusAtual(StatusProjeto.CANCELADO);
        projetoRepository.save(projetoExistente);

        return "Projeto cancelado com sucesso.";
    }
}
