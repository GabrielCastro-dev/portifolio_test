package portifolioTest.portofolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.enums.RiscoProjeto;
import portifolioTest.portofolio.enums.StatusProjeto;
import portifolioTest.portofolio.repository.ProjetoMembroRepository;
import portifolioTest.portofolio.repository.ProjetoRepository;
import portifolioTest.portofolio.utils.StatusValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoMembroRepository projetoMembroRepo;

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

    public List<Projeto> listByStatus(StatusProjeto statusDesejado){
        List<Projeto> projetosEncontrados = this.projetoRepository.findByStatusAtual(statusDesejado);

        if(projetosEncontrados.isEmpty()){
            throw new RuntimeException("Nenhum projeto encontrado nessa categoria.");
        }

        return projetosEncontrados;
    }

    public List<Projeto> listByRisco(RiscoProjeto riscoDesejado){
        List<Projeto> projetosEncontrados = this.projetoRepository.findByRisco(riscoDesejado);

        if(projetosEncontrados.isEmpty()){
            throw new RuntimeException("Nenhum projeto encontrado em esse nível de risco.");
        }

        return projetosEncontrados;
    }

    public String deleteById(Long id){
        Projeto projetoADeletar = findById(id);

        if(!StatusValidator.podeExcluir(projetoADeletar.getStatusAtual())){
            throw new RuntimeException(
                "Projetos só podem ser excluido em fase de analise, planejamento ou cancelamento." +
                "\nStatus atual do projeto: " + projetoADeletar.getStatusAtual()
            );
        }

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

    private void validarTransicao(Projeto projeto, StatusProjeto novoStatus, boolean permitirEncerramentoDireto) {
        // Valida se o projeto já foi encerrado
        if (projeto.getStatusAtual() == StatusProjeto.ENCERRADO) {
            throw new RuntimeException("Este projeto já foi encerrado.");
        }

        // Valida se é atualização padrão ou encerramento
        if (!permitirEncerramentoDireto && novoStatus == StatusProjeto.ENCERRADO) {
            throw new RuntimeException("Operação inválida. Utilize a rota \"projeto/encerrar/{id}\" para finalizar o projeto.");
        }

        // Valida se a progressão de status se adequa às regras de negócio
        if (!StatusValidator.podeTransicionar(projeto.getStatusAtual(), novoStatus)) {
            if (novoStatus == StatusProjeto.ENCERRADO) {
                throw new RuntimeException("Transição de status inválida: só podem ser encerrados os projetos em andamento.");
            } else {
                throw new RuntimeException("Transição de status inválida: " +
                        projeto.getStatusAtual() + " → " + novoStatus);
            }
        }
    }

    // Atualização apenas para status que não forem "ENCERRADO"
    public Projeto atualizarStatusProjeto(Long id, StatusProjeto statusAtualizado) {
        Projeto projetoExistente = findById(id);

        validarTransicao(projetoExistente, statusAtualizado, false);

        projetoExistente.setStatusAtual(statusAtualizado);
        return projetoRepository.save(projetoExistente);
    }

    // Atualização de status para encerramento
    public Projeto encerrarProjeto(Long id, LocalDate dataTerminoEfetivo) {
        Projeto projetoExistente = findById(id);

        validarTransicao(projetoExistente, StatusProjeto.ENCERRADO, true);

        // Valida data de termino efetivo
        if (!dataTerminoEfetivo.isAfter(projetoExistente.getDataInicio())) {
            throw new RuntimeException(
                    "Data de término efetivo inválida." +
                    "\nData de início do projeto: " + projetoExistente.getDataInicio() +
                    "\nData de término fornecida: " + dataTerminoEfetivo
            );
        }

        projetoExistente.setDataTerminoEfetivo(dataTerminoEfetivo);
        projetoExistente.setStatusAtual(StatusProjeto.ENCERRADO);

        return projetoRepository.save(projetoExistente);
    }

//    public void associarMembro(Long projetoId, Long memberId) {
//        Projeto projeto = projetoRepository.findById(projetoId)
//                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
//
//        // consulta API externa
//        MembroDTO membro = membrosExternalAPI.getMembroById(memberId);
//
//        // Valida membro
//        if (membro == null) {
//            throw new RuntimeException("Membro inválido ou inativo");
//        }
//
//        if (!"funcionário".equalsIgnoreCase(membro.getPapel())) {
//            throw new RuntimeException("Somente 'funcionário' pode ser alocado");
//        }
//
//        // cria a relação
//        ProjetoMembro pm = new ProjetoMembro();
//        pm.setProjeto(projeto);
//        pm.setMemberId(memberId);
//
//        projetoMembroRepo.save(pm);
//    }
}
