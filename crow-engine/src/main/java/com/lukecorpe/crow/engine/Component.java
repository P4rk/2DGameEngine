package com.lukecorpe.crow.engine;

import com.lukecorpe.crow.engine.interfaces.Updatable;

public abstract class Component implements Updatable {
	private Game game;
	public Game Game(){
		return game;
	}
	public Game getGame() {
		return game;
	}
	public Component(Game game){
		this.game = game;
	}
}
