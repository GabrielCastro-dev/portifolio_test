package portifolioTest.portofolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portifolioTest.portofolio.dto.ProjetoDTO;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.mapper.ProjetoMapper;
import portifolioTest.portofolio.service.ProjetoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<?> postProjeto(@RequestBody ProjetoDTO projetoDTO){
        try {
            Projeto novoProjeto = ProjetoMapper.toEntity(projetoDTO);
            String response = this.projetoService.postProjeto(novoProjeto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao criar novo projeto!");
        }
    }

    @GetMapping
    public ResponseEntity<?> getProjetos(){
        try {
            List<Projeto> projetos = this.projetoService.getProjetos();
            List<ProjetoDTO> response = projetos.stream()
                    .map(ProjetoMapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao carregar projetos!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjetoById(@PathVariable Long id){
        try {
            Projeto projeto = this.projetoService.findById(id);
            ProjetoDTO response = ProjetoMapper.toDTO(projeto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao buscar projeto!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjetoById(@PathVariable Long id){
        try {
            String response = this.projetoService.deleteById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProjetoById(@PathVariable Long id, @RequestBody ProjetoDTO projetoDTO){
        try {
            Projeto projetoAtualizado = ProjetoMapper.toEntity(projetoDTO);
            String response = this.projetoService.editById(id, projetoAtualizado);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao editar projeto como um todo!");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> cancelarProjeto(@PathVariable Long id){
        try {
            String response = this.projetoService.cancelarProjeto(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
