package com.tullyo.spring.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tullyo.spring.auth.assembler.UsuarioAssembler;
import com.tullyo.spring.auth.domain.entity.Usuario;
import com.tullyo.spring.auth.dto.UsuarioResponseTO;
import com.tullyo.spring.auth.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity<UsuarioResponseTO> salvar(@RequestBody UsuarioRequestTO usuarioRequestTO) {
		String senha = passwordEncoder.encode(usuarioRequestTO.getSenha());
		usuarioRequestTO.setSenha(senha);
		Usuario usuario = UsuarioAssembler.from(usuarioRequestTO);
		usuarioService.salvar(usuario);

		UsuarioResponseTO usuarioResponseTO = UsuarioAssembler.from(usuario);
		ResponseEntity<UsuarioResponseTO> resposne = ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseTO);
		return resposne;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioResponseTO>> listarTodos() {
		List<Usuario> usuarios = usuarioService.findAll();
		List<UsuarioResponseTO> usuariosResponseTO = UsuarioAssembler.from(usuarios);
		
		ResponseEntity<List<UsuarioResponseTO>> response = ResponseEntity.status(HttpStatus.ACCEPTED).body(usuariosResponseTO);
		return response;
	}
	
}
