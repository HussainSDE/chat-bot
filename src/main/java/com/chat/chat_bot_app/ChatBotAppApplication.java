package com.chat.chat_bot_app;

import com.chat.chat_bot_app.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ChatBotAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBotAppApplication.class, args);
	}

}
