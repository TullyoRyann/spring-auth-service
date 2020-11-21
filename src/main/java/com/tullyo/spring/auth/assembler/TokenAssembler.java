package com.tullyo.spring.auth.assembler;

import com.tullyo.spring.auth.dto.TokenTO;

public class TokenAssembler {
	
	public static TokenTO from(String login, String token) {
		return TokenTO
				.builder()
				.login(login)
				.token(token)
				.build();
	}

}
