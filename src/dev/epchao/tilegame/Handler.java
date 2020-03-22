package dev.epchao.tilegame;

import dev.epchao.tilegame.gfx.GameCamera;
import dev.epchao.tilegame.input.KeyManager;
import dev.epchao.tilegame.input.MouseManager;
import dev.epchao.tilegame.worlds.World;

//manages all of the games return statemenets, passes it along to different classes w/o making the game class messy
public class Handler {

	private Game game;
	private World world; 
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
