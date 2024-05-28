package br.com.terceiroperiodo.repository;

import br.com.terceiroperiodo.model.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    public Page<Cidade> findAll(Pageable pageable);

}
