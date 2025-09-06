package portifolioTest.portofolio.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portifolioTest.portofolio.dto.MembroDTO;
import portifolioTest.portofolio.security.SecurityConfig;
import portifolioTest.portofolio.service.MembroService;

import java.util.List;

@RestController
@RequestMapping("/membro")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class MembroController {

    @Autowired
    private  MembroService membroService;

    @GetMapping("/{id}")
    public MembroDTO getMembroById(@PathVariable Long id) {
        return membroService.getMembroById(id);
    }

    @PostMapping
    public MembroDTO createMembro(@RequestBody MembroDTO membro) {
        return membroService.createMembro(membro);
    }

    @GetMapping
    public List<MembroDTO> getAllMembros() {
        return membroService.getAllMembros();
    }
}
