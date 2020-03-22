package dev.epchao.tilegame.entities.statics;

import dev.epchao.tilegame.Handler;
import dev.epchao.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity{

	//abstract class for static objects like trees or rocks
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	
}
