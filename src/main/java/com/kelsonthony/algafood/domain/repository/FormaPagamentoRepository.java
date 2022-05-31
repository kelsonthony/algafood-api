package com.kelsonthony.algafood.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kelsonthony.algafood.domain.model.FormaPagamento;


@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

	@Query("select max(dataAtualizacao) from FormaPagamento")
	OffsetDateTime getDataUltimaAtualizacao();
	
	@Query("select max(dataAtualizacao) from FormaPagamento where id = :formaPagamentoId")
	OffsetDateTime getDataUltimaAtualizacaoById(Long formaPagamentoId);
}
