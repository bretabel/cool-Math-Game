package horsegame;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;


public class HorseGame extends PApplet {

	//Instance Variables
	//TODO: Fill in variables
	
	//Fonts
	PFont menufont;
	
	// Images
	PImage background;
	
	//Coordinates
	
	//Menu Buttons
	int startX;
	int startY;
	int startWidth;
	int startHeight;
	
	//Game State
	enum GameState {
		MENU, RUNNING, GAMEOVER
	}
	static GameState currentState;
	
		
	/**
	 * Initialize Variables	
	 */
	public void setup() {
		//fonts
		
		//Initialize Game State
		currentState = GameState.MENU;
		
		//
	}
	
	public void settings() {
		size(900,900);
	}

	/**
	 * The method that draws all the animations to screen
	 */
	public void draw() {
		switch (currentState) {
		
		case MENU:
			drawMenu();
			break;
		
		case RUNNING:
			drawRunning();
			break;
			
		case GAMEOVER:
			drawGameOver();
			break;
		}
	}
	
	/**
	 * Method to draw the Menu
	 */
	private void drawMenu() {
		clear();
		background(0, 0, 0);

		textAlign(CENTER, CENTER);
		fill(90, 200, 215); // teal
		text("Menu", width / 2, height / 2 - 200);

		fill(200);
		text("start", width / 2, height / 2 - 20);
		text("credits", width / 2, height / 2 + 20);	}
	
	/**
	 * Method to draw the game while it runs
	 */
	private void drawRunning() {
		//TODO: Fill in
	}
	
	/**
	 * Method to draw the Game Over window.
	 */
	private void drawGameOver() {
		//TODO: Fill in
	}
	
	/**
	 * Method to draw the player to the screen
	 */
	private void drawPlayer() {
		
	}
	
	public void mousePressed() {
		switch(currentState) {
		case MENU:
			if(mouseX > startX && mouseX < startX + startWidth && mouseY > startY && mouseY < startY + startHeight) {
				currentState = GameState.RUNNING;
			}
		}
	}
	
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { horsegame.HorseGame.class.getName() });
	}
}
