package dev.epchao.tilegame.states;

import java.awt.Graphics;

import dev.epchao.tilegame.Handler;
import dev.epchao.tilegame.worlds.World;

public class GameState extends State{
	
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	public void tick() {
		world.tick();
	}

	public void render(Graphics g) {
		world.render(g);
		
	}

}
