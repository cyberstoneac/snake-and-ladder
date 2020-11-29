package com.test.snakeandladder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board implements Serializable {

	private static final long serialVersionUID = -7983798605682771625L;
	private int size;
	private List<Snake> snakes;
	private List<Ladder> ladders;
	private Map<String, Integer> pieces;

	public Board(int size) {
		this.size = size;
		this.snakes = new ArrayList<Snake>();
		this.ladders = new ArrayList<Ladder>();
		this.pieces = new HashMap<String, Integer>();
	}

	public int getSize() {
		return size;
	}

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

	public Map<String, Integer> getPieces() {
		return pieces;
	}

	public void setPieces(Map<String, Integer> pieces) {
		this.pieces = pieces;
	}

}
