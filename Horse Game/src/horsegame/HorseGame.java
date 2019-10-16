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
	PImage gameBG;
	
	//Coordinates
	int boxX, boxY, boxW, boxH;					 // text box for questions 	
	
	int startX, startY, startWidth, startHeight; // start button
	
	//Game State
	enum GameState {
		MENU, RUNNING, GAMEOVER
	}
	static GameState currentState;
	
	//Game Assets
	ProblemSet questions;
	
		
	/**
	 * Initialize Variables	
	 */
	public void setup() {
		//fonts
		
		//Initialize Game State
		currentState = GameState.MENU;
		
		//Images
		gameBG = loadImage("Game_Background.png");
		gameBG.resize(0, 900);
		
		//Assets
		questions = new ProblemSet();
		
		//Text Box
		boxW = width;
		boxH = 100;
		boxX = 0;
		boxY = height - boxH;
		
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
		text("credits", width / 2, height / 2 + 20);	
		}
	
	/**
	 * Method to draw the game while it runs
	 */
	private void drawRunning() {
		clear();
		background(gameBG);
		
	}
	
	public void drawTextBox() {
		
	}
	
	/**
	 * Gets a question to query the user
	 */
	public void getQuestion() {
		questions.getFirst();
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
			break;
			
		case RUNNING:
			break;
			
		case GAMEOVER:
			break;
		}
	}
	
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { horsegame.HorseGame.class.getName() });
	}
}
