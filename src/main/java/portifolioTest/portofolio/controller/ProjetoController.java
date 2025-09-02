package portifolioTest.portofolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import portifolioTest.portofolio.entity.Projeto;
import portifolioTest.portofolio.service.ProjetoService;

import java.util.List;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {
    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<String> postProjeto(@RequestBody Projeto novoProjeto){
        try {
            String response = this.projetoService.postProjeto(novoProjeto);
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao criar novo projeto!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getProjetos(){
        try {
            List<Projeto> response = this.projetoService.getProjetos();
            return new ResponseEntity<List<Projeto>>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao carregar projetos!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjetoById(@PathVariable Long id){
        try {
            Projeto response = this.projetoService.findById(id);
            return new ResponseEntity<Projeto>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao buscar projeto!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjetoById(@PathVariable Long id){
        try {
            String response = this.projetoService.deleteById(id);
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editProjetoById(@PathVariable Long id, @RequestBody Projeto projetoAtualizado){
        try {
            String response = this.projetoService.editById(id, projetoAtualizado);
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao editar projeto como um todo!", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> cancelarProjeto(@PathVariable Long id){
        try {
            String response = this.projetoService.cancelarProjeto(id);
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao cancelar projeto!", HttpStatus.BAD_REQUEST);
        }
    }
}
