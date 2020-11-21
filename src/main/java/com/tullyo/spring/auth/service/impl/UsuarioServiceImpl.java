package com.tullyo.spring.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tullyo.spring.auth.domain.entity.Usuario;
import com.tullyo.spring.auth.domain.repository.UsuarioRepository;
import com.tullyo.spring.auth.exception.SenhaInvalidaException;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvar(Usuario usuario) {
		if(usuario.getRoles() == null || !usuario.getRoles().contains("USER")) {
			usuario.addRole("USER");
		}
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public UserDetails autenticar(Usuario usuario) {
		UserDetails userDetails = loadUserByUsername(usuario.getLogin());
		boolean isPasswordEquals = encoder.matches(usuario.getSenha(), userDetails.getPassword());
		if(isPasswordEquals) {
			return userDetails;
		}
		
		throw new SenhaInvalidaException();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
		
		return User
				.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles("USER")
				.build();
	}

}
