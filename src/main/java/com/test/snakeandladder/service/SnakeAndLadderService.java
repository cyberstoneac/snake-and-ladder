package com.test.snakeandladder.service;

import java.util.List;

import com.test.snakeandladder.dto.SnakeAndLadderDto;
import com.test.snakeandladder.model.Player;

public interface SnakeAndLadderService {

	public List<Player> startGame(SnakeAndLadderDto dto);
	
}
