package com.kelsonthony.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kelsonthony.algafood.domain.model.Grupo;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long>, JpaSpecificationExecutor<Grupo> {
	
	List<Grupo> findAll();
}
