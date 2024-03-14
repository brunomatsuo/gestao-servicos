package br.com.servicos.controller;

import br.com.servicos.model.Servico;
import br.com.servicos.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @GetMapping
    public ResponseEntity getAll() {
        List<Servico> servicos = servicoService.getAll();
        return servicos.size() > 0 ? ResponseEntity.ok(servicos) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Servico servico = servicoService.getById(id);
        return servico != null ? ResponseEntity.ok(servico) : ResponseEntity.notFound().build();
    }

    @GetMapping("/nome")
    public ResponseEntity getByNome(@RequestParam String nome) {
        List<Servico> servicos = servicoService.getByNome(nome);
        return servicos.size() > 0 ? ResponseEntity.ok(servicos) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity createServico(@RequestBody Servico servico) {
        Servico created = servicoService.addServico(servico);
        return created != null ? ResponseEntity.created(URI.create(created.getId().toString())).body(created) : ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editServico(@PathVariable String id, @RequestBody Servico servico) {
        Servico updated = servicoService.editServico(servico, id);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteServico(@PathVariable String id) {
        Boolean removed = servicoService.removeServico(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/criarServicosAuto")
    public ResponseEntity criarServicos() {
        return ResponseEntity.created(URI.create("ok")).body(servicoService.createServicos());
    }
}
