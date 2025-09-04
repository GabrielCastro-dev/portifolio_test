package portifolioTest.portofolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.entity.StatusProjeto;
import portifolioTest.portofolio.repository.ProjetoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    public Projeto postProjeto(Projeto projeto){
        projeto.calcularRisco();
        Projeto salvo = this.projetoRepository.save(projeto);
        return salvo;
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

    public Projeto editById(Long id, Projeto projetoAtualizado){
        Projeto projetoExistente = findById(id);

        // Atualiza os campos
        projetoExistente.setNome(projetoAtualizado.getNome());
        projetoExistente.setDataInicio(projetoAtualizado.getDataInicio());
        projetoExistente.setDataTerminoPrevisto(projetoAtualizado.getDataTerminoPrevisto());
        projetoExistente.setDataTerminoEfetivo(projetoAtualizado.getDataTerminoEfetivo());
        projetoExistente.setOrcamentoTotal(projetoAtualizado.getOrcamentoTotal());
        projetoExistente.setDescricao(projetoAtualizado.getDescricao());
        projetoExistente.setStatusAtual(projetoAtualizado.getStatusAtual());

        // Recalcula risco se necessário
        projetoExistente.calcularRisco();

        projetoRepository.save(projetoExistente);

        return projetoExistente;
    }

    public Projeto cancelarProjeto(Long id){
        Projeto projetoExistente = findById(id);

        if(projetoExistente.getStatusAtual() == StatusProjeto.CANCELADO){
            throw new RuntimeException("Este projeto já foi cancelado.");
        }

        projetoExistente.setStatusAtual(StatusProjeto.CANCELADO);
        projetoRepository.save(projetoExistente);

        return projetoExistente;
    }

    public Projeto encerrarProjeto(Long id, LocalDate dataTerminoEfetivo){
        // Para finalizar o projeto é necessária uma data de termino efetivo
        Projeto projetoExistente = findById(id);

        if(projetoExistente.getStatusAtual() == StatusProjeto.ENCERRADO){
            throw new RuntimeException("Este projeto já foi encerrado.");
        }

        projetoExistente.setDataTerminoEfetivo(dataTerminoEfetivo);
        projetoExistente.setStatusAtual(StatusProjeto.ENCERRADO);

        projetoRepository.save(projetoExistente);

        return projetoExistente;
    }
}
