package portifolioTest.portofolio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portifolioTest.portofolio.dto.ProjetoMembroDTO;
import portifolioTest.portofolio.entity.ProjetoMembro;
import portifolioTest.portofolio.mapper.ProjetoMembroMapper;
import portifolioTest.portofolio.security.SecurityConfig;
import portifolioTest.portofolio.service.ProjetoMembroService;

import java.util.List;

@RestController
@RequestMapping("/projeto-membro")
@Tag(name = "Projeto-Membro", description = "Operações para alocação de membros em projetos")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class ProjetoMembroController {

    @Autowired
    private ProjetoMembroService projetoMembroService;

    @Operation(summary = "Lista todos os membros alocados em projetos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro ao listar membros",
                    content = @Content())
    })
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<ProjetoMembro> lista = projetoMembroService.listarTodos();
            List<ProjetoMembroDTO> dtoList = ProjetoMembroMapper.toDTOList(lista);
            return ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao listar membros do projeto!");
        }
    }

    @Operation(summary = "Aloca um membro em um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Membro alocado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Erro ao alocar membro",
                    content = @Content())
    })
    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody ProjetoMembroDTO dto) {
        try {
            ProjetoMembro entidade = ProjetoMembroMapper.toEntity(dto);
            ProjetoMembro salvo = projetoMembroService.adicionarMembro(entidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(ProjetoMembroMapper.toDTO(salvo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao alocar membro no projeto!");
        }
    }

    @Operation(summary = "Atualiza dados da alocação de um membro em um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Membro atualizado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar membro",
                    content = @Content())
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ProjetoMembroDTO dto) {
        try {
            ProjetoMembro pm = ProjetoMembroMapper.toEntity(dto);
            ProjetoMembro atualizado = projetoMembroService.atualizarRelacao(id, pm);
            return ResponseEntity.ok(ProjetoMembroMapper.toDTO(atualizado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao atualizar membro do projeto!");
        }
    }

    @Operation(summary = "Remove um membro de um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Membro removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao remover membro",
                    content = @Content())
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            projetoMembroService.removerMembro(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao remover membro do projeto!");
        }
    }

    @Operation(summary = "Busca uma alocação de membro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Membro encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Membro não encontrado",
                    content = @Content())
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            ProjetoMembro membro = projetoMembroService.buscarPorId(id);
            return ResponseEntity.ok(ProjetoMembroMapper.toDTO(membro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Membro não encontrado no projeto!");
        }
    }
}
