package com.lucas.cadastro.auth;

import com.lucas.cadastro.config.JwtService;
import com.lucas.cadastro.enums.Role;
import com.lucas.cadastro.model.Usuario;
import com.lucas.cadastro.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UsuarioRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		var user = Usuario.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.ROLE_USER)
				.build();
		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	 public AuthenticationResponse authenticate(AuthenticationRequest request) {
		    authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(
		            request.getUsername(),
		            request.getPassword()
		        )
		    );
		    var user = repository.findUserByUsername(request.getUsername()).orElseThrow();
		    var jwtToken = jwtService.generateToken(user);
		    return AuthenticationResponse.builder().token(jwtToken).build();
		  }
}