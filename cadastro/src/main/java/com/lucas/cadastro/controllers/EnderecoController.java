package com.lucas.cadastro.controllers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lucas.cadastro.model.Endereco;
import com.lucas.cadastro.service.EnderecoService;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoServ;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll(){
		List<Endereco> endereco = enderecoServ.findAll();
		return ResponseEntity.ok().body(endereco);
	}
	
	@PostMapping
	public ResponseEntity<Endereco> insert(@RequestBody Endereco endereco) throws Exception{
		
		URL url = new URL("https://viacep.com.br/ws/"+endereco.getCep()+"/json/");
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		String cep = "";
		StringBuilder jsonCep = new StringBuilder();
		
		while((cep = br.readLine()) != null) {
			jsonCep.append(cep);
		}
		 
		Endereco enderAux = new Gson().fromJson(jsonCep.toString(), Endereco.class);
		endereco.setCep(enderAux.getCep());
		endereco.setLogradouro(enderAux.getLogradouro());
		endereco.setComplemento(enderAux.getComplemento());
		endereco.setBairro(enderAux.getBairro());
		endereco.setLocalidade(enderAux.getLocalidade());
		endereco.setUf(enderAux.getUf());
		
		
		endereco = enderecoServ.save(endereco);
		return ResponseEntity.ok().body(endereco);
	}
	
}
