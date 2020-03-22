package dev.epchao.tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.epchao.tilegame.Handler;
import dev.epchao.tilegame.entities.Entity;
import dev.epchao.tilegame.gfx.Animation;
import dev.epchao.tilegame.gfx.Assets;
import dev.epchao.tilegame.inventory.Inventory;

public class Player extends Creature{
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	//Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	//Inventory
	private Inventory inventory;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 32;
		bounds.width = 18;
		bounds.height = 31;
		
		//Animations
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		
		inventory = new Inventory(handler);
	}
	
	public void die() {
		System.out.println("you suck");
	}

	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		//KeyManager
		getInput();
		//Movement
		move();
		//CenterCamera on player
		handler.getGameCamera().centerOnEntity(this);
		//Attack
		checkAttacks();
		//Inventory
		inventory.tick();
	}
	
	//detect if the player is attacking an entity
	public void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) {
			return;
		}
		if(inventory.isActive()) {
			return;
		}
		//collision bounds
		Rectangle cb = getCollisionBounds(0, 0);
		//attack range
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}
		else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}
		else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else if(handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(inventory.isActive()) {
			return;
		}
		
		if(handler.getKeyManager().up) {
			yMove = -speed;
		}
		if (handler.getKeyManager().down) {
			yMove = speed;
		}
		if (handler.getKeyManager().left) {
			xMove = -speed;
		}
		if (handler.getKeyManager().right) {
			xMove = speed;
		}
	}

	public void render(Graphics g) {
		//instead of hard coding the animations, you can just create a method that'll return an animation based on movement
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) { //Move Left
			return animLeft.getCurrentFrame();
		}
		else if (xMove > 0){ //Move Right
			return animRight.getCurrentFrame();
		}
		else if (yMove < 0) { //Moving Up
			return animUp.getCurrentFrame();
		}
		else { //Moving Down or Idle
			return animDown.getCurrentFrame();
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
