package portifolioTest.portofolio.utils;

import portifolioTest.portofolio.entity.StatusProjeto;

import java.util.List;

public class StatusValidator {
    private static final List<StatusProjeto> ORDEM = List.of(
            StatusProjeto.EM_ANALISE,
            StatusProjeto.ANALISE_REALIZADA,
            StatusProjeto.ANALISE_APROVADA,
            StatusProjeto.INICIADO,
            StatusProjeto.PLANEJADO,
            StatusProjeto.EM_ANDAMENTO,
            StatusProjeto.ENCERRADO
    );

    public static boolean podeTransicionar(StatusProjeto atual, StatusProjeto proximo) {
        int indexAtual = ORDEM.indexOf(atual);
        int indexProximo = ORDEM.indexOf(proximo);

        return indexProximo == indexAtual + 1;
    }
}
