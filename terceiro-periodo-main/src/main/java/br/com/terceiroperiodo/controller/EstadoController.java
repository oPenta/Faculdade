package br.com.terceiroperiodo.controller;

import br.com.terceiroperiodo.model.Estado;
import br.com.terceiroperiodo.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    @PostMapping()
    public ResponseEntity<Estado> salvarEstado(@RequestBody Estado estado){

        Estado response = estadoService.salvar(estado);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Estado>> buscarTodos(){

        List<Estado> response = estadoService.buscarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Estado> buscarPorId(@PathVariable Long id){

        Optional<Estado> response = estadoService.buscarPorId(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<List<Estado>> buscarPorNome(@PathVariable String nome){

        List<Estado> response = estadoService.buscarPorNome(nome);
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Estado> update(@RequestBody Estado estado){

        if (!estadoService.buscarPorId(estado.getId()).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estadoService.atualizar(estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
         return estadoService.deleteById(id);
    }



}
