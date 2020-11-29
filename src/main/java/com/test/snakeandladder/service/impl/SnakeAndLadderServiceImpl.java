package com.test.snakeandladder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.test.snakeandladder.constants.DiceType;
import com.test.snakeandladder.dto.SnakeAndLadderDto;
import com.test.snakeandladder.model.Board;
import com.test.snakeandladder.model.Ladder;
import com.test.snakeandladder.model.Player;
import com.test.snakeandladder.model.Snake;
import com.test.snakeandladder.util.DiceUtil;
import com.test.snakeandladder.service.SnakeAndLadderService;

@Service
public class SnakeAndLadderServiceImpl implements SnakeAndLadderService {

	private Board board;
	private int initialNumberOfPlayers;
	private Queue<Player> players;
	private Map<Player, Integer> moves;

	@Value("${snake.ladder.dice.count}")
	private int noOfDices;

	@Value("${snake.ladder.board.size}")
	private int boardSize;

	@Value("${snake.ladder.moves.allowed}")
	private int movesAllowed;

	@Override
	public List<Player> startGame(SnakeAndLadderDto dto) {
		List<Player> winners = new ArrayList<>();
		setupBoard(dto);
		while (!isGameCompleted()) {
			int totalDiceValue = getTotalValueAfterDiceRolls(dto.getDiceType());
			Player currentPlayer = players.poll();
			movePlayer(currentPlayer, totalDiceValue);
			if (hasPlayerWon(currentPlayer)) {
				System.out.println(currentPlayer.getName() + " wins the game");
				winners.add(currentPlayer);
				board.getPieces().remove(currentPlayer.getId());
			} else {
				players.add(currentPlayer);
			}
			if (this.moves.containsKey(currentPlayer) && this.moves.get(currentPlayer) == movesAllowed) {
				board.getPieces().remove(currentPlayer.getId());
			}
		}
		return winners;
	}

	private void setupBoard(SnakeAndLadderDto dto) {
		this.board = new Board(boardSize);
		setPlayers(dto.getPlayers());
		board.setSnakes(dto.getSnakes());
		board.setLadders(dto.getLadders());
	}

	private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) {
		int previousPosition;
		do {
			previousPosition = newPosition;
			for (Snake snake : board.getSnakes()) {
				if (snake.getStart() == newPosition) {
					newPosition = snake.getEnd();
				}
			}

			for (Ladder ladder : board.getLadders()) {
				if (ladder.getStart() == newPosition) {
					newPosition = ladder.getEnd();
				}
			}
		} while (newPosition != previousPosition);
		return newPosition;
	}

	private void movePlayer(Player player, int positions) {
		int oldPosition = board.getPieces().get(player.getId());
		int newPosition = oldPosition + positions;

		int boardSize = board.getSize();

		if (newPosition > boardSize) {
			newPosition = oldPosition;
		} else {
			newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
		}

		board.getPieces().put(player.getId(), newPosition);
		if (this.moves.containsKey(player)) {
			this.moves.put(player, this.moves.get(player) + 1);
		} else {
			this.moves.put(player, 1);
		}
		System.out.println(
				player.getName() + " rolled a " + positions + " and moved from " + oldPosition + " to " + newPosition);
	}

	private int getTotalValueAfterDiceRolls(DiceType type) {
		int diceValue = 0;
		for (int i = 0; i < noOfDices; i++) {
			diceValue = DiceUtil.roll(type);
		}
		return diceValue;
	}

	private boolean hasPlayerWon(Player player) {
		int playerPosition = board.getPieces().get(player.getId());
		int winningPosition = board.getSize();
		return playerPosition == winningPosition;
	}

	private boolean isGameCompleted() {
		int currentNumberOfPlayers = players.size();
		return currentNumberOfPlayers < initialNumberOfPlayers || board.getPieces().isEmpty();
	}

	public void setPlayers(List<Player> players) {
		this.players = new LinkedList<Player>();
		this.moves = new HashMap<Player, Integer>();
		this.initialNumberOfPlayers = players.size();
		Map<String, Integer> playerPieces = new HashMap<String, Integer>();
		for (Player player : players) {
			this.players.add(player);
			playerPieces.put(player.getId(), 0);
		}
		board.setPieces(playerPieces);
	}

}
