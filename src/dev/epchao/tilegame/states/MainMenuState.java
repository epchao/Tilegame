package dev.epchao.tilegame.states;

import java.awt.Graphics;

import dev.epchao.tilegame.Handler;
import dev.epchao.tilegame.gfx.Assets;
import dev.epchao.tilegame.ui.ClickListener;
import dev.epchao.tilegame.ui.UIImageButton;
import dev.epchao.tilegame.ui.UIManager;

public class MainMenuState extends State{

	private UIManager uiManager;
	
	public MainMenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);;
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {

			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				
			}}));
	}
	
	public void tick() {
		uiManager.tick();
	}

	public void render(Graphics g) {
		uiManager.render(g);
	}

}
