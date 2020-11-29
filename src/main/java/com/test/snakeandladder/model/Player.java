package com.test.snakeandladder.model;

import java.io.Serializable;
import java.util.UUID;

public class Player implements Serializable {

	private static final long serialVersionUID = 2391452786877228969L;
	private String name;
	private String id;

	public Player() {
		super();
	}

	public Player(String name) {
		this.name = name;
		this.id = UUID.randomUUID().toString();
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

}
