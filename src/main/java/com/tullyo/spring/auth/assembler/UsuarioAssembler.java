package com.tullyo.spring.auth.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.tullyo.spring.auth.controller.UsuarioRequestTO;
import com.tullyo.spring.auth.domain.entity.Usuario;
import com.tullyo.spring.auth.dto.UsuarioResponseTO;

public class UsuarioAssembler {
	
	public static Usuario from(UsuarioRequestTO usuarioRequestTO) {
		return new Usuario(
				usuarioRequestTO.getLogin(), 
				usuarioRequestTO.getSenha());
	}
	
	public static UsuarioResponseTO from(Usuario usuario) {
		return new UsuarioResponseTO(
				usuario.getId(), 
				usuario.getLogin(),
				usuario.getRoles());
	}

	public static List<UsuarioResponseTO> from (List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioAssembler::from).collect(Collectors.toList());
	}
	
}
