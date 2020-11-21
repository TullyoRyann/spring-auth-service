package com.tullyo.spring.auth.dto;

import lombok.Data;

@Data
public class TokenTO {
	
    private String login;
    private String token;
}
