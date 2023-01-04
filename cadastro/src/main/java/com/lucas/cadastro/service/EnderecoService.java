package com.lucas.cadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.cadastro.model.Endereco;
import com.lucas.cadastro.repository.EnderecoRepository;

@Service
public class EnderecoService {

	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Endereco>findAll(){
		return enderecoRepository.findAll();
	}
	
	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
}
