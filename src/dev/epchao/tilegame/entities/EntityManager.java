package dev.epchao.tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.epchao.tilegame.Handler;
import dev.epchao.tilegame.entities.creatures.Player;

public class EntityManager {

	//hard coding entities to the world isn't effective, so we can use a manager to manage all the entities we want in the world
	private Handler handler;
	private Player player;
	//Array list has no size
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderMachine = new Comparator<Entity>() {

		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
			 	return -1;
			else
				return 1;
		}
		
	};
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	public void tick() {
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()) {
			Entity e = it.next();
			e.tick();
			if(!e.isActive())
				it.remove();
		}
		entities.sort(renderMachine);
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
		player.postRender(g);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	//GETTERS AND SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
}
