package com.test.snakeandladder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.snakeandladder.dto.SnakeAndLadderDto;
import com.test.snakeandladder.model.Player;
import com.test.snakeandladder.service.SnakeAndLadderService;

@RestController
public class SnakeAndLadderController {

	@Value("${snake.ladder.secret.key}")
	private String secretKey;

	@Autowired
	private SnakeAndLadderService service;

	@GetMapping("/snakeAndLadder/startGame")
	public ResponseEntity<List<Player>> startGame(@RequestBody SnakeAndLadderDto dto, HttpServletRequest request) {
		if (!secretKey.equalsIgnoreCase(request.getHeader("secretKey"))) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(service.startGame(dto), HttpStatus.OK);
	}

}
