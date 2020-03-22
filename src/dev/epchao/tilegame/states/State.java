package dev.epchao.tilegame.states;

import java.awt.Graphics;

import dev.epchao.tilegame.Handler;

//defining all the properties a state (main menu, settings, game) should have
public abstract class State {

	// START OF GAME STATE MANAGER
	private static State currentState = null;
	
	//getters and setters to store and change the current state of the game 
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	// END OF GAME STATE MANAGER
	
	//CLASS
	
	//passing game object through states
	protected Handler handler;
	
	//constructor
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
