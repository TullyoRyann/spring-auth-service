package com.tullyo.spring.auth.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioRequestTO {

	private String login;
	
	private String senha;
	
}
