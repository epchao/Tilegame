package dev.epchao.tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display{

    //using a JFrame to create the window of our game
    private JFrame frame;
    //using a Canvas to draw stuff onto our screen
    private Canvas canvas;

    //window takes in these 3 parameters
    private String title;
    private int width, height;

    //constructor
    public Display(String title, int width, int height){
        //initializing variables when this constructor gets called
        this.title = title;
        this.width = width;
        this.height = height;
        //created new method so it won't clutter the constructor
        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);
        //define the window's width and height 
        frame.setSize(width, height);
        //how the program will close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ability to move the window around the screen
        frame.setResizable(false);
        //center window
        frame.setLocationRelativeTo(null);
        //window appear
        frame.setVisible(true);

        //creating a canvas to draw something onto the window
        canvas = new Canvas();
        //adjusting the minimum, maximum, and preferred size of our canvas to be the width and height of our window
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        //focus on application, rather than canvas
        canvas.setFocusable(false);

        //add the canvas to the frame and then make the canvas be within the frame and not able to go out of it
        frame.add(canvas);
        frame.pack();
    }

    //not recommended to make the Canvas public, so this is the way to allow other classes to use the Canvas class
    public Canvas getCanvas() {
        return canvas;
    }

    //same as getCanvas
    public JFrame getFrame() {
    	return frame;
    }
    
}