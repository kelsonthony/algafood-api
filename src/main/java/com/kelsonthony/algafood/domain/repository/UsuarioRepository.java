package com.kelsonthony.algafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kelsonthony.algafood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long>, 
					JpaSpecificationExecutor<Usuario> {

	Optional<Usuario> findByEmail(String email);

}
