package com.tullyo.spring.auth.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tullyo.spring.auth.domain.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiracao}")
	private String expiracao;

	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	
	public String gerarToken(Usuario usuario) {
		long _expiracao = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(_expiracao);
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		return Jwts
				.builder()
				.setSubject(usuario.getLogin())
				.setExpiration(data)
				.signWith( SignatureAlgorithm.HS512, this.chaveAssinatura )
				.compact();
	}
	
	public boolean tokenValido(String token) {
		try {
			Claims claims = getClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return LocalDateTime.now().isBefore(data);
		} catch (Exception e) {
			return false;
		}
	}

	private Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}

	public String getLoginUsuario(String token) {
		return String.valueOf((getClaims(token).getSubject())); 
	}
}
