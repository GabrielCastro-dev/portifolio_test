package portifolioTest.portofolio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portifolioTest.portofolio.dto.CreateMembroDTO;
import portifolioTest.portofolio.dto.MembroDTO;
import portifolioTest.portofolio.entity.Membro;
import portifolioTest.portofolio.mapper.MembroMapper;
import portifolioTest.portofolio.security.SecurityConfig;
import portifolioTest.portofolio.service.MembroService;

import java.util.List;

@RestController
@RequestMapping("/membro")
@Tag(name = "Membro", description = "Controlador para todas operações com entidade Membro")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class MembroController {

    @Autowired
    private MembroService membroService;

    @Operation(summary = "Busca membro por ID", description = "Retorna um membro específico pelo ID informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Membro encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MembroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Membro não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Erro na requisição",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getMembroById(@PathVariable Long id) {
        try {
            Membro membroEncontrado = membroService.findById(id);
            MembroDTO dto = MembroMapper.toDTO(membroEncontrado);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Cria membro", description = "Cria e retorna um novo membro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Membro criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MembroDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao criar membro",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<?> createMembro(@RequestBody CreateMembroDTO membroCriado) {
        try {
            Membro convertido = MembroMapper.toEntity(membroCriado);
            Membro salvo = membroService.postMembro(convertido);
            MembroDTO response = MembroMapper.toDTO(salvo);
            return ResponseEntity.status(201).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Lista todos os membros", description = "Retorna todos os membros cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de membros retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MembroDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao listar membros",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping
    public ResponseEntity<?> getAllMembros() {
        try {
            List<Membro> membros = membroService.getMembros();
            List<MembroDTO> membrosDTO = MembroMapper.toDTOList(membros);
            return ResponseEntity.ok(membrosDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Atualiza membro", description = "Atualiza os dados de um membro pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Membro atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MembroDTO.class))),
            @ApiResponse(responseCode = "404", description = "Membro não encontrado",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar membro",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> editMembro(@PathVariable Long id, @RequestBody CreateMembroDTO membroAtualizado) {
        try {
            Membro convertido = MembroMapper.toEntity(membroAtualizado);
            Membro atualizado = membroService.putMembro(id, convertido);
            MembroDTO response = MembroMapper.toDTO(atualizado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
