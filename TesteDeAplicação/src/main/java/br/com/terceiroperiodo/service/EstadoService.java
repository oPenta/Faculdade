package br.com.terceiroperiodo.service;

import br.com.terceiroperiodo.model.Estado;
import br.com.terceiroperiodo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    public Estado salvar(Estado estado){
        estado.setAtivo(true);
        return estadoRepository.save(estado);
    }

    public List<Estado> buscarTodos(){
        List<Estado> response = estadoRepository.findAll();
        response.removeIf(estado -> !estado.getAtivo());
        return response;
    }

    public Optional<Estado> buscarPorId(Long id){
        return estadoRepository.findById(id);
    }

    public Estado atualizar(Estado estado){
        return estadoRepository.save(estado);
    }

    public List<Estado> buscarPorNome(String nome){
        return estadoRepository.findByNomeAndAtivo(nome, true);
    }

    public ResponseEntity<?> deleteById(Long id) {

        Optional<Estado> response = buscarPorId(id);

        if (!response.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        response.get().setAtivo(false);

        return ResponseEntity.ok(estadoRepository.save(response.get())) ;
    }

}
