package dev.epchao.tilegame;

public class Launcher {
    
    public static void main(String[] args){
        //new object named game
        Game game = new Game("Tile Game", 800, 600);
        game.start();
    }
}