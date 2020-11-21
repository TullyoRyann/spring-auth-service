package com.tullyo.spring.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenTO {
	
    private String login;
    private String token;
}
