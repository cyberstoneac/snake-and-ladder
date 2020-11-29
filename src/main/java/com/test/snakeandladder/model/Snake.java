package com.test.snakeandladder.model;

import java.io.Serializable;

public class Snake implements Serializable {
	private static final long serialVersionUID = 86151714675190788L;
	private int start;
	private int end;

	public Snake(int start, int end) {
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
