package com.kelsonthony.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kelsonthony.algafood.domain.model.FormaPagamento;


@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

	
}
