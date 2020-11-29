package com.test.snakeandladder.model;

import java.io.Serializable;

public class Ladder implements Serializable {

	private static final long serialVersionUID = -3001603503828735814L;
	private int start;
	private int end;

	public Ladder(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

}
