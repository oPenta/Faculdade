package br.com.terceiroperiodo.controller;

import br.com.terceiroperiodo.model.Cidade;
import br.com.terceiroperiodo.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    CidadeService cidadeService;

    @PostMapping()
    public ResponseEntity<Cidade> salvarCidade(@RequestBody Cidade cidade){

        Cidade response = cidadeService.salvar(cidade);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Cidade>> buscarTodos(){

        List<Cidade> response = cidadeService.buscarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/pageable/all", produces = "application/json")
    public ResponseEntity<Page<Cidade>> buscarTodosPaginado(
            @RequestParam Integer page,
            @RequestParam Integer size){

        Page<Cidade> response = cidadeService.
                getAllCidadesByPage(page, size);
        return ResponseEntity.ok(response);
    }

}
