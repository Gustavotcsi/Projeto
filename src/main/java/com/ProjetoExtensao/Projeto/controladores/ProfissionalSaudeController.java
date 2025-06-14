package com.ProjetoExtensao.Projeto.controladores;

import com.ProjetoExtensao.Projeto.entidades.ProfissionalSaude;
import com.ProjetoExtensao.Projeto.servicos.ProfissionalSaudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalSaudeController {

    @Autowired
    private ProfissionalSaudeService profissionalSaudeService;

    @PostMapping
    public ResponseEntity<ProfissionalSaude> criarProfissional(@RequestBody ProfissionalSaude profissional) {
        try {
            ProfissionalSaude novoProfissional = profissionalSaudeService.salvarProfissional(profissional);
            return new ResponseEntity<>(novoProfissional, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalSaude>> listarProfissionais() {
        List<ProfissionalSaude> profissionais = profissionalSaudeService.listarTodosProfissionais();
        return new ResponseEntity<>(profissionais, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalSaude> buscarProfissionalPorId(@PathVariable Long id) {
        Optional<ProfissionalSaude> profissional = profissionalSaudeService.buscarProfissionalPorId(id);
        return profissional.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalSaude> atualizarProfissional(@PathVariable Long id,
                                                                   @RequestBody ProfissionalSaude profissional) {
        try {
            ProfissionalSaude profissionalAtualizado = profissionalSaudeService.atualizarProfissional(id, profissional);
            return new ResponseEntity<>(profissionalAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarProfissional(@PathVariable Long id) {
        try {
            profissionalSaudeService.deletarProfissional(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
