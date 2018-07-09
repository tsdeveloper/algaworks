package com.algaworks.cobranca.Repository;

import com.algaworks.cobranca.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {
}
