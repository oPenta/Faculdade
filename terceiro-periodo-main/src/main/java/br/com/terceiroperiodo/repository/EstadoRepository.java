package br.com.terceiroperiodo.repository;

import br.com.terceiroperiodo.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    List<Estado> findByNomeAndAtivo(String nome, Boolean status);

}
