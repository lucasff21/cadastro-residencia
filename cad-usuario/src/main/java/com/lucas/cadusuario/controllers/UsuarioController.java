package com.lucas.cadusuario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.cadusuario.model.Usuario;
import com.lucas.cadusuario.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServ;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> user = usuarioServ.findAll();
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> save(@RequestBody Usuario user){
		user = usuarioServ.save(user);
		return ResponseEntity.ok().body(user);
	}
	
}
