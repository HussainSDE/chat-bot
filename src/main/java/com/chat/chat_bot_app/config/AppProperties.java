package com.chat.chat_bot_app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

	private final Auth auth = new Auth();
	private final Jwt jwt = new Jwt();
	private final Cors cors = new Cors();

	public Auth getAuth() {
		return auth;
	}

	public Jwt getJwt() {
		return jwt;
	}

	public Cors getCors() {
		return cors;
	}

	public static class Auth {
		private String username;
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	public static class Jwt {
		private String secret;
		private long expirationMs;

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}

		public long getExpirationMs() {
			return expirationMs;
		}

		public void setExpirationMs(long expirationMs) {
			this.expirationMs = expirationMs;
		}
	}

	public static class Cors {
		private String allowedOrigins;

		public String getAllowedOrigins() {
			return allowedOrigins;
		}

		public void setAllowedOrigins(String allowedOrigins) {
			this.allowedOrigins = allowedOrigins;
		}
	}
}
