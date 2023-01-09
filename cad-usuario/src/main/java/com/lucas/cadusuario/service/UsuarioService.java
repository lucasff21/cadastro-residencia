package com.lucas.cadusuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.cadusuario.model.Usuario;
import com.lucas.cadusuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	 public List<Usuario> findAll(){
		 return usuarioRepo.findAll();
	 }
	 
	 public Usuario findById(Long id){
		 Optional<Usuario> user = usuarioRepo.findById(id);
		 return user.get();
	 }
	 
	 public Usuario save(Usuario user) {
		 return usuarioRepo.save(user);
	 }
}
