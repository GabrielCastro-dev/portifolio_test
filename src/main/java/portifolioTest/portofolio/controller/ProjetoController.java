package portifolioTest.portofolio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portifolioTest.portofolio.dto.CreateProjetoDTO;
import portifolioTest.portofolio.dto.MembroDTO;
import portifolioTest.portofolio.dto.ProjetoDTO;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.entity.RiscoProjeto;
import portifolioTest.portofolio.entity.StatusProjeto;
import portifolioTest.portofolio.mapper.ProjetoMapper;
import portifolioTest.portofolio.security.SecurityConfig;
import portifolioTest.portofolio.service.ProjetoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projeto")
@Tag(name = "Projeto", description = "Controlador para todas operações com entidade Projeto")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    @Operation(summary = "Cria projeto", description = "Método para criar projeto")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Projeto criado com sucesso",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ProjetoDTO.class)
                )
        ),
        @ApiResponse(responseCode = "400", description = "Erro ao criar projeto",
                content = @Content(schema = @Schema()))
    })
    public ResponseEntity<?> postProjeto(@RequestBody CreateProjetoDTO projetoDTO){
        try {
            Projeto novoProjeto = ProjetoMapper.toEntity(projetoDTO);
            Projeto salvo = projetoService.postProjeto(novoProjeto);
            ProjetoDTO response = ProjetoMapper.toDTO(salvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao criar novo projeto!");
        }
    }

    @GetMapping
    @Operation(summary = "Lista todos projetos do sistema", description = "Método para listar projetos do sitema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projetos listados com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProjetoDTO.class))
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro ao listar projetos",
                    content = @Content(schema = @Schema())
            )
    })
    public ResponseEntity<?> getProjetos(){
        try {
            List<Projeto> projetos = projetoService.getProjetos();
            List<ProjetoDTO> response = ProjetoMapper.toDTOList(projetos);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao carregar projetos!");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna projeto por id", description = "Método para retornar um projeto específico por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetoDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro ao buscar projeto",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<?> getProjetoById(@PathVariable Long id){
        try {
            Projeto projeto = projetoService.findById(id);
            ProjetoDTO response = ProjetoMapper.toDTO(projeto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao buscar projeto!");
        }
    }

    @GetMapping("/porStatus")
    @Operation(summary = "Lista produdos por status", description = "Método para listar projetos por status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projetos listados com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProjetoDTO.class))
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro ao listar projetos",
                    content = @Content(schema = @Schema())
            )
    })
    public ResponseEntity<?> getProjetosByStatus(@RequestParam String statusDesejado){
        try {
            StatusProjeto statusEnum = StatusProjeto.valueOf(statusDesejado.toUpperCase());
            List<Projeto> projetos = projetoService.listByStatus(statusEnum);
            List<ProjetoDTO> response = ProjetoMapper.toDTOList(projetos);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/porRisco")
    @Operation(summary = "Lista produdos por risco", description = "Método para listar projetos por risco")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projetos listados com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProjetoDTO.class))
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro ao listar projetos",
                    content = @Content(schema = @Schema())
            )
    })
    public ResponseEntity<?> getProjetosByRisco(@RequestParam String riscoDesejado){
        try {
            RiscoProjeto riscoEnum = RiscoProjeto.valueOf(riscoDesejado.toUpperCase());
            List<Projeto> projetos = projetoService.listByRisco(riscoEnum);
            List<ProjetoDTO> response = ProjetoMapper.toDTOList(projetos);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta projeto por id", description = "Método para deletar um projeto específico por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto deletado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetoDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar projeto",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<String> deleteProjetoById(@PathVariable Long id){
        try {
            String response = projetoService.deleteById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita projeto", description = "Método para editar objeto completo do projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto editado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetoDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro ao editar projeto",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<?> editProjetoById(@PathVariable Long id, @RequestBody CreateProjetoDTO projetoDTO){
        try {
            Projeto projetoAtualizado = ProjetoMapper.toEntity(projetoDTO);
            Projeto salvo = projetoService.editById(id, projetoAtualizado);
            ProjetoDTO response = ProjetoMapper.toDTO(salvo);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao editar projeto como um todo!");
        }
    }

    @PatchMapping("/cancelar/{id}")
    @Operation(summary = "Cancela projeto", description = "Método para cancelar projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto cancelado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetoDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro ao cancelar projeto",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<?> cancelarProjeto(@PathVariable Long id){
        try {
            Projeto cancelado = projetoService.cancelarProjeto(id);
            ProjetoDTO response = ProjetoMapper.toDTO(cancelado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/atualizarStatus/{id}")
    @Operation(summary = "Edita status projeto", description = "Método para editar projeto com base na regra sequencial")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Status do projeto atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetoDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar status do projeto",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<?> atualizarStatusProjeto(@PathVariable Long id, @RequestBody StatusProjeto statusAtualizado){
        try {
            Projeto projetoAtualizado = projetoService.atualizarStatusProjeto(id, statusAtualizado);
            ProjetoDTO response = ProjetoMapper.toDTO(projetoAtualizado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/finalizar/{id}")
    @Operation(summary = "Finaliza projeto", description = "Método para finalizar projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Projeto finalizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetoDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Erro ao finalizar projeto",
                    content = @Content(schema = @Schema()))
    })
    public ResponseEntity<?> encerrarProjeto(@PathVariable Long id, @RequestBody LocalDate dataTerminoEfetivo){
        try {
            Projeto projetoFinalizado = projetoService.encerrarProjeto(id, dataTerminoEfetivo);
            ProjetoDTO response = ProjetoMapper.toDTO(projetoFinalizado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
