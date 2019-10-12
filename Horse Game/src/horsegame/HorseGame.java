package horsegame;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;


public class HorseGame extends PApplet {

	//Instance Variables
	//TODO: Fill in variables
	
	// Images
	PImage background;
	
	//Coordinates
	
	//Game State
	enum GameState {
		MENU, RUNNING, GAMEOVER
	}
	static GameState currentState;
	
		
		
	public void setup() {
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
		//TODO: Fill in
	}
	
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
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { horsegame.HorseGame.class.getName() });
	}
}
