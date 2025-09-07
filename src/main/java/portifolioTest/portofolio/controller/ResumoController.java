package portifolioTest.portofolio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portifolioTest.portofolio.dto.MembroDTO;
import portifolioTest.portofolio.dto.ResumoDTO;
import portifolioTest.portofolio.security.SecurityConfig;
import portifolioTest.portofolio.service.ResumoService;

@RestController
@RequestMapping("/resumo")
@Tag(name = "Resumo do portifólio", description = "Rota para interagir com o resumo do portifólio")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class ResumoController {

    @Autowired
    private ResumoService resumoService;

    @GetMapping
    @Operation(summary = "Gera um relatório do sistema",
            description = "Gera um relatorio com base nas entidades Projeto, Membro e ProjetoMembro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resumo gerado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResumoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro na requisição",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<?> getResumo(){
        try {
            ResumoDTO resumo = resumoService.gerarResumo();
            return new ResponseEntity<ResumoDTO>(resumo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
