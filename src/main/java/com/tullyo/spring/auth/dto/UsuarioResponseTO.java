package com.tullyo.spring.auth.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioResponseTO {

	private Long id;
	private String login;
	private List<String> roles;
}
