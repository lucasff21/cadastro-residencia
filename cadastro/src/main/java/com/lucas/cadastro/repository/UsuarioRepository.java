package com.lucas.cadastro.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lucas.cadastro.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findUserByUsername(String username);
	
}
