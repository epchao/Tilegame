package dev.epchao.tilegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	//defined proportions of sprites
	private static final int WIDTH = 32, HEIGHT = 32;
	
	public static Font font28;
	
	public static BufferedImage dirt, grass, stone, tree, rock;
	public static BufferedImage wood;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;

	public static void init(){
		font28 = fontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		//define sprite sheet
		Spritesheet sheet = new Spritesheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		wood = sheet.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
		
		//menu
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(WIDTH * 6, HEIGHT * 4, WIDTH * 2, HEIGHT);
		btn_start[1] = sheet.crop(WIDTH * 6, HEIGHT * 5, WIDTH * 2, HEIGHT);
		
		//initalizing player
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		//player with animation work
		player_down[0] = sheet.crop(WIDTH * 4, 0, WIDTH, HEIGHT);
		player_down[1] = sheet.crop(WIDTH * 5, 0, WIDTH, HEIGHT);
		player_up[0] = sheet.crop(WIDTH * 6, 0, WIDTH, HEIGHT);
		player_up[1] = sheet.crop(WIDTH * 7, 0, WIDTH, HEIGHT);
		player_right[0] = sheet.crop(WIDTH * 4, HEIGHT, WIDTH, HEIGHT);
		player_right[1] = sheet.crop(WIDTH * 5, HEIGHT, WIDTH, HEIGHT);
		player_left[0] = sheet.crop(WIDTH * 6, HEIGHT, WIDTH, HEIGHT);
		player_left[1] = sheet.crop(WIDTH * 7, HEIGHT, WIDTH, HEIGHT);
		
		//initalizing enemies
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		//enemy with animation work
		zombie_down[0] = sheet.crop(WIDTH * 4, HEIGHT * 2, WIDTH, HEIGHT);
		zombie_down[1] = sheet.crop(WIDTH * 5, HEIGHT * 2, WIDTH, HEIGHT);
		zombie_up[0] = sheet.crop(WIDTH * 6, HEIGHT * 2, WIDTH, HEIGHT);
		zombie_up[1] = sheet.crop(WIDTH * 7, HEIGHT * 2, WIDTH, HEIGHT);
		zombie_right[0] = sheet.crop(WIDTH * 4, HEIGHT * 3, WIDTH, HEIGHT);
		zombie_right[1] = sheet.crop(WIDTH * 5, HEIGHT * 3, WIDTH, HEIGHT);
		zombie_left[0] = sheet.crop(WIDTH * 6, HEIGHT * 3, WIDTH, HEIGHT);
		zombie_left[1] = sheet.crop(WIDTH * 7, HEIGHT * 3, WIDTH, HEIGHT);
		
		//tiles
		dirt = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
		grass = sheet.crop(WIDTH * 2, 0, WIDTH, HEIGHT);
		stone = sheet.crop(WIDTH * 3, 0, WIDTH, HEIGHT);
		//entities
		tree = sheet.crop(0, 0, WIDTH, HEIGHT * 2);
		rock = sheet.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
	}
	
}
