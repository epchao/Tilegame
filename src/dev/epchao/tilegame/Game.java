package dev.epchao.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.epchao.tilegame.display.Display;
import dev.epchao.tilegame.gfx.Assets;
import dev.epchao.tilegame.gfx.GameCamera;
import dev.epchao.tilegame.input.KeyManager;
import dev.epchao.tilegame.input.MouseManager;
import dev.epchao.tilegame.states.GameState;
import dev.epchao.tilegame.states.MainMenuState;
import dev.epchao.tilegame.states.State;

//similar to Display class, to create a Thread, you need to implement Runnable
public class Game implements Runnable {

    // declaring variables to initialize a "game"
    private Display display;
    private int width, height;
    public String title;

    // variables needed to run game on thread
    private Boolean running = false;
    private Thread thread;

    // variables to render graphics
    private BufferStrategy bs; // tells the computer how to draw stuff to a screen with a hidden screen in your
                               // screen (prevents flickering)
    private Graphics g;

    //States
    public State gameState;
    public State mainMenuState;
    
    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    //Camera
    private GameCamera gameCamera;
    
    //Handler
    private Handler handler;
    
    // creating a constructor for the game
    public Game(String title, int width, int height) {
        // declaring its width and height just like the display class
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    //Initialize graphics of the game
    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0,0);
        
        
        gameState = new GameState(handler);
        mainMenuState = new MainMenuState(handler);
        State.setState(mainMenuState);
    }

    //update variables & positions of objects for the game
    private void tick(){
    	keyManager.tick();
    	
    	if(State.getState() != null) {
    		State.getState().tick();
    	}
    }

    //draw/render graphics of the game
    private void render(){
        //set Buffer Strategy to whatever canvas is on the display
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        //allows us to draw pictures to the canvas
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);
        //draw here
        if(State.getState() != null) {
    		State.getState().render(g);
    	}
        //stop draw
        //essential code to show graphics
        bs.show();
        g.dispose();
    }

    // Runnable needs to have a run method in the class
    // will have majority of game code in
    public void run() {
        init();

        //game loop
        //equalize movement of game between fast computers and slow computers 
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(running){
        	now = System.nanoTime();
        	delta += (now - lastTime) / timePerTick;
        	timer += now - lastTime;
        	lastTime = now;
        	
        	if(delta >= 1) {
        		tick();
        		render();
        		ticks++;
        		delta--;
        	}
        	if(timer >= 1000000000) {
        		System.out.println("Ticks and Frames: " + ticks);
        		ticks = 0;
        		timer = 0;
        	}
        }

        stop();
    }

    //allows entity classes to access the key manager
    public KeyManager getKeyManager() {
    	return keyManager;
    }
    
    public MouseManager getMouseManager() {
    	return mouseManager;
    }
    
    //allows classes to access the game camera
    public GameCamera getGameCamera() {
    	return gameCamera;
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
    
    // these two synchronized methods will start/stop the thread
    // synchronized - used when working with threads directly, so nothing gets
    // messed up
    public synchronized void start() {
        if (running)
            return;
        running = true;
        //the parameter that the thread is taking is this class (Game Class)
        thread = new Thread(this);
        //start method will run the run method
        thread.start();
    }

    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        //close thread properly
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}