package dev.epchao.tilegame.worlds;

import java.awt.Graphics;

import dev.epchao.tilegame.Handler;
import dev.epchao.tilegame.entities.EntityManager;
import dev.epchao.tilegame.entities.creatures.Player;
import dev.epchao.tilegame.entities.statics.Rock;
import dev.epchao.tilegame.entities.statics.Tree;
import dev.epchao.tilegame.items.ItemManager;
import dev.epchao.tilegame.tiles.Tile;
import dev.epchao.tilegame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	//Item
	private ItemManager itemManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		//Test entities
		entityManager.addEntity(new Tree(handler, 100, 250));
		entityManager.addEntity(new Rock(handler, 100, 450));
		entityManager.addEntity(new Tree(handler, 100, 650));
		entityManager.addEntity(new Rock(handler, 100, 850));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		entityManager.tick();
		itemManager.tick();
	}
	
	public void render(Graphics g) {
		//is 0 (furtherest left tile) greater than the camera's x offset / tile_width?
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		//is the farthest tile to right less than the camera's x offset / tile width (+1 is to get rid of choppy glitch)
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), 
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		//if doesn't find tile, it'll default to a dirt tile
		if(t == null) {
			return Tile.dirtTile;
		}
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		//split every single element into new index into an array called tokens
		String[] tokens = file.split("\\s+");
		//converting the integers from our world text file into data to fill our class variables
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		//convert world text file data into tiles
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width; x ++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
}
