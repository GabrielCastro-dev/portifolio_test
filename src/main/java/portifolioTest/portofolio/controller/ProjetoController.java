package portifolioTest.portofolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portifolioTest.portofolio.dto.CreateProjetoDTO;
import portifolioTest.portofolio.dto.ProjetoDTO;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.entity.StatusProjeto;
import portifolioTest.portofolio.mapper.ProjetoMapper;
import portifolioTest.portofolio.service.ProjetoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
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
    public ResponseEntity<?> getProjetos(){
        try {
            List<Projeto> projetos = projetoService.getProjetos();
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
            Projeto projeto = projetoService.findById(id);
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
            String response = projetoService.deleteById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
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
