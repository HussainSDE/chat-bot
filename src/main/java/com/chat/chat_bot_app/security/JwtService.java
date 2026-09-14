package com.chat.chat_bot_app.security;

import com.chat.chat_bot_app.config.AppProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

	private final AppProperties appProperties;

	public JwtService(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	private SecretKey signingKey() {
		String secret = appProperties.getJwt().getSecret();
		byte[] keyBytes;
		try {
			keyBytes = Base64.getDecoder().decode(secret);
		} catch (IllegalArgumentException ex) {
			keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		}
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(String username) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + appProperties.getJwt().getExpirationMs());
		return Jwts.builder()
				.subject(username)
				.issuedAt(now)
				.expiration(expiry)
				.signWith(signingKey())
				.compact();
	}

	public String extractUsername(String token) {
		return parseClaims(token).getSubject();
	}

	public boolean isTokenValid(String token) {
		try {
			Claims claims = parseClaims(token);
			return claims.getExpiration().after(new Date());
		} catch (JwtException | IllegalArgumentException ex) {
			return false;
		}
	}

	private Claims parseClaims(String token) {
		return Jwts.parser()
				.verifyWith(signingKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
