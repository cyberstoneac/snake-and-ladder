package com.test.snakeandladder.dto;

import java.io.Serializable;
import java.util.List;

import com.test.snakeandladder.constants.DiceType;
import com.test.snakeandladder.model.Ladder;
import com.test.snakeandladder.model.Player;
import com.test.snakeandladder.model.Snake;

public class SnakeAndLadderDto implements Serializable {

	private static final long serialVersionUID = -8123611204698137368L;
	List<Snake> snakes;
	List<Ladder> ladders;
	List<Player> players;
	DiceType diceType;

	public List<Snake> getSnakes() {
		return snakes;
	}

	public void setSnakes(List<Snake> snakes) {
		this.snakes = snakes;
	}

	public List<Ladder> getLadders() {
		return ladders;
	}

	public void setLadders(List<Ladder> ladders) {
		this.ladders = ladders;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public DiceType getDiceType() {
		return diceType;
	}

	public void setDiceType(DiceType diceType) {
		this.diceType = diceType;
	}

}
