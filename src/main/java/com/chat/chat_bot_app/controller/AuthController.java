package com.chat.chat_bot_app.controller;

import com.chat.chat_bot_app.config.AppProperties;
import com.chat.chat_bot_app.dto.LoginRequest;
import com.chat.chat_bot_app.dto.LoginResponse;
import com.chat.chat_bot_app.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AppProperties appProperties;
	private final JwtService jwtService;

	public AuthController(AppProperties appProperties, JwtService jwtService) {
		this.appProperties = appProperties;
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		String expectedUsername = appProperties.getAuth().getUsername();
		String expectedPassword = appProperties.getAuth().getPassword();

		boolean valid = expectedUsername.equals(request.username())
				&& expectedPassword.equals(request.password());

		if (!valid) {
			return ResponseEntity.status(401).build();
		}

		String token = jwtService.generateToken(request.username());
		return ResponseEntity.ok(new LoginResponse(token, request.username()));
	}
}
