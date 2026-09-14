package com.chat.chat_bot_app.controller;

import com.chat.chat_bot_app.dto.ChatRequest;
import com.chat.chat_bot_app.dto.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	@PostMapping
	public ChatResponse chat(@RequestBody ChatRequest request) {
		String reply = "This is a test response from the chat bot. You said: \"" + request.message() + "\"";
		return new ChatResponse(reply);
	}
}
