package br.com.servicos.controller;

import br.com.servicos.model.Item;
import br.com.servicos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseEntity getAll() {
        List<Item> items = itemService.getAll();
        return items.size() > 0 ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Item item = itemService.getById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @GetMapping("/nome")
    public ResponseEntity getByNome(@RequestParam String nome) {
        List<Item> items = itemService.getByNome(nome);
        return items.size() > 0 ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity createItem(@RequestBody Item item) {
        Item created = itemService.addItem(item);
        return created != null ? ResponseEntity.created(URI.create(created.getId().toString())).body(created) : ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editItem(@RequestBody Item item, @PathVariable String id) {
        Item edited = itemService.editItem(item, id);
        return edited != null ? ResponseEntity.ok(edited) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeItem(@PathVariable String id) {
        Boolean removed = itemService.removeItem(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/criarItensAuto")
    public ResponseEntity criarItens() {
        return ResponseEntity.created(URI.create("ok")).body(itemService.createItems());
    }
}
