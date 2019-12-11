package horsegame;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

public class HorseGame extends PApplet {

	// Instance Variables
	// TODO: Fill in variables as needed

	// Fonts
	PFont menufont;

	// Images
	PImage gameBG;
	PImage startMenuBG;
	PImage gameOverBG;
	PImage creditsBG;
	PImage backButton;
	PImage exitButton;
	PImage creditsButton;
	PImage startButton;
	PImage restartButton;
	PImage menuButton;
	PImage player;
	PImage ground;
	PImage hills;
	PImage mountains;
	PImage[] sprites = new PImage[8];
	int animationFrame = 1;

	// Coordinates
	int boxX, boxY; 		// text box for questions
	int startX, startY; 	// start button
	int exitX, exitY; 		// exit button
	int credX, credY; 		// credits button
	int restartX, restartY; // restart button
	int menuX, menuY; 		// menu button
	final int PLAYER_X = 100;
	int playerX, playerY;	// player sprite
	int scoreX, scoreY;		// score
	int bgx1 = 0;
	int bgx2 = 0;
	int bgx3 = 0;
	int endX;
	int timerX, timerY;		// timer

	// Dimensions
	int boxW, boxH; 		// text box width and height
	int butW, butH; 		// button width and height



	// Game State
	enum GameState {
		MENU, RUNNING, GAMEOVER, CREDITS
	}

	static GameState currentState;

	// Game Assets
	ProblemSet questions;
	TextBox textBox;
	int score;
	final int MAX_SCORE = 10;
	int startTime;
	int timer;
	int stopTime;
	int missed; 	//failed attempts to answer questions
	double accuracy;
	double avg;

	/**
	 * Initialize Variables
	 */
	public void setup() {

		frameRate(90);

		// Initialize Game State
		currentState = GameState.MENU;

		// Images
		gameBG = loadImage("Game_Background.png");
		gameBG.resize(1125, 650);
		backButton = loadImage("Back_Button.png");
		backButton.resize(75, 75);
		exitButton = loadImage("Exit_Button.png");
		exitButton.resize(300, 0);
		creditsButton = loadImage("Credits_Button.png");
		creditsButton.resize(300, 0);
		startButton = loadImage("Start_Button.png");
		startButton.resize(300, 0);
		restartButton = loadImage("StartOver_Button.png");
		restartButton.resize(300, 0);
		menuButton = loadImage("MainMenu_Button.png");
		menuButton.resize(300, 0);
		player = loadImage("Frame1.png");
		player.resize(200, 0);
		ground = loadImage("Ground.png");
		hills = loadImage("Hills.png");
		mountains = loadImage("Mountains.png");
		sprites[0] = loadImage("Frame1.png");
		sprites[1] = loadImage("Frame2.png");
		sprites[2] = loadImage("Frame3.png");
		sprites[3] = loadImage("Frame4.png");
		sprites[4] = loadImage("Frame5.png");
		sprites[5] = loadImage("Frame6.png");
		sprites[6] = loadImage("Frame7.png");
		sprites[7] = loadImage("Frame8.png");
		for (int i = 0; i < sprites.length; i++) {
			sprites[i].resize(200, 0);
		}
		startMenuBG = loadImage("MainMenu_BG.png");
		startMenuBG.resize(1125,  650);
		gameOverBG = loadImage("GameOverMenu_BG.png");
		gameOverBG.resize(1125, 650);
		creditsBG = loadImage("Credits_BG.png");
		creditsBG.resize(1125, 650);

		// Assets
		questions = new ProblemSet();
		score = 0;
		startTime = 0;		// timer gets initialized each time game starts
		timer = 0;
		stopTime = 0;
		missed = 0;

		// Text Box
		boxW = width / 2;
		boxH = 50;
		boxX = width / 4;
		boxY = height - boxH - 20;
		textBox = new TextBox(boxX, boxY, boxW, boxH);

		// Buttons (based on the pixel dimensions of the image file)
		butW = 300;
		butH = 112;

		// Coordinates
		startX = width / 2 - butW / 2;
		startY = height / 2 - 120;
		restartX = width / 2 - butW / 2;
		restartY = height / 2 - 120;
		credX = width / 2 - butW / 2;
		credY = height / 2;
		exitX = width / 2 - butW / 2;
		exitY = height / 2 + 120;
		scoreX = 125;
		scoreY = height - 75;
		timerX = width - 125;
		timerY = height - 75;

		// player coordinates
		playerX = PLAYER_X;
		playerY = 440;
		endX = 100;
	}

	public void settings() {
		size(1125, 650); //Started as 1125 900
	}

////////////////////////////////////////////////////////////
///////////// DRAWING METHODS  /////////////////////////////
////////////////////////////////////////////////////////////

	/**
	 * The method that draws all the animations to screen
	 */
	public void draw() {
		switch (currentState) {

		case MENU:
			drawMenu();
			break;

		case RUNNING:

			timer = (millis() - startTime) / 1000;
			if (frameCount % 6 == 0) {
				animationFrame++;
				animationFrame = animationFrame % sprites.length;
			}
			drawRunning();
			break;

		case GAMEOVER:
			drawGameOver();
			break;

		case CREDITS:
			drawCredits();
			break;
		}
	}

	/**
	 * Method to draw the Menu
	 */
	private void drawMenu() {
		clear();
		background(startMenuBG);

		textAlign(CENTER, CENTER);
		textSize(45);
		fill(255, 255, 255); // white

		image(startButton, startX, startY);
		image(creditsButton, credX, credY);
		image(exitButton, exitX, exitY);
	}

	/**
	 * Method to draw the game while it runs
	 */
	private void drawRunning() {
		if (score == MAX_SCORE) {
			stopTime = timer;
			currentState = GameState.GAMEOVER;
		}
		clear();
		background(gameBG);
		drawMountains();
		drawHills();
		drawGround();
		drawPlayer();
		drawTextBox();
		drawQuestions();
		drawScore();
		drawTimer();

	}

	/**
	 * Method to draw the Game Over window.
	 */
	private void drawGameOver() {
		accuracy = (10/((double)missed+10))*100;
		avg = ((double)stopTime/10);
		clear();
		background(gameOverBG);
		imageMode(CORNER);
		image(restartButton, restartX, restartY);
		image(creditsButton, credX, credY);
		image(exitButton, exitX, exitY);
		textAlign(CENTER, CENTER);
		textSize(45);
		fill(255, 255, 255); // white
		text("Game Over!", width / 2, height / 2 - 250);
		textSize(32);
		text("You answered " + score + " questions in " + stopTime + " seconds with " + missed + " misses!", width / 2, height / 2 - 200);
		textSize(24);
		text("Your answers were " + ((int)(100*accuracy)/100) +"% accurate and you spent an average of "+ avg +" seconds on each question!", width / 2, height / 2 - 150);
		endX = 100;
	}

	private void drawCredits() {
		clear();
		background(creditsBG);

		image(backButton, 0, 0);

		textAlign(CENTER, CENTER);
		textSize(45);
		fill(255, 255, 255); // white
		text("Credits", width / 2, height / 2 - 200);
		textSize(32);
		text("Developed by:", width / 2, height / 2 - 150);
		text("Bret", width / 2, height / 2 - 100);
		text("Kyle", width / 2, height / 2 - 50);
		text("Ellis", width / 2, height / 2 );
		text("Darian", width / 2, height / 2 + 50);
		text("Dominic", width / 2, height / 2 + 100);
	}

	/**
	 * Method to draw the player to the screen
	 */
	private void drawPlayer() {
		imageMode(CENTER);
		image(sprites[animationFrame], playerX, playerY);
		playerX++;
		if (playerX >= endX) {
			playerX = endX;
			frameRate(90);
		}
	}

	private void drawMountains() {
		image(mountains, bgx1/2, 250);
		image(mountains, bgx1/2 + mountains.width, 250);
		bgx1--;
		if (bgx1/2 < -mountains.width) {
			bgx1 = 0;
		}
	}

	private void drawHills() {
		image(hills, bgx2, 350);
		image(hills, bgx2 + hills.width, 350);
		bgx2--;
		if (bgx2 < -hills.width) {
			bgx2 = 0;
		}
	}

	private void drawGround() {
		image(ground, bgx3*3, 470);
		image(ground, bgx3*3 + ground.width, 470);
		bgx3--;
		if (bgx3*3 < -ground.width) {
			bgx3 = 0;
		}
	}

	private void drawMoveForward() {
		// TODO
	}

	private void drawScore() {
		textAlign(CENTER, CENTER);
		fill(255, 255, 255); // white
		text(("Missed:\n" + missed), scoreX, scoreY);
	}

	/**
	 * Method to draw the timer
	 */
	private void drawTimer() {
		textAlign(CENTER, CENTER);
		fill(255, 255, 255); // white
		text("Timer:\n" + timer, timerX, timerY);
	}

	private void drawTextBox() {
		textBox.draw();
	}

	/**
	 * Method to draw the questions to the screen
	 */
	private void drawQuestions() {
		Expression q;
		if (questions.problemList.size() >= 1) {
			 q = questions.getFirst();
		} else {
			q = new Expression();
			questions.addExpression(q);				// this should never happen
		}
		String qString = q.toString();
		fill(140);									// same shade of grey as the text box
		text(qString, boxX, boxY - 20);
		if (questionAnswered(q) == 1) {
			score++;
			endX = playerX + width/ (MAX_SCORE + 2);
			frameRate(140);
			//playerX += width/ (MAX_SCORE + 2);		// moves the player sprite across the screen
			questions.problemList.remove(0);
			textBox.clear();
		}
		else if (questionAnswered(q) == 0) {
			textBox.clear();
			missed++;
		}
	}


///////////////////////////////////////////////////////////
//////    EVENT HANDLING METHODS //////////////////////////
///////////////////////////////////////////////////////////

	/**
	 * A method to check the player's answer against the solution
	 * @param q The question
	 * @return 1 if correct, 0 if incorrect, -1 if enter not pressed
	 */
	private int questionAnswered(Expression q) {
		int input = -100;				// placeholder value
		if(!(textBox.isEmpty())){
			if(textBox.Text.length() < 4) {
				input = Integer.parseInt(textBox.Text);
			}
			if(keyCode == ENTER) {
				if(q.isSolution(input)) {
					return 1;
				}
				return 0;
			}
		}
		return -1;
	}

	/**
	 * Uses the default PApplet keyPressed() method as a wrapper for the text box.
	 */
	public void keyPressed() {
		textBox.keyPressed(key, keyCode);
	}

	/**
	 * Handles mouse click events
	 */
	public void mousePressed() {
		switch (currentState) {
		case MENU:
			// start buttons
			if (mouseX > startX && mouseX < startX + butW && mouseY > startY && mouseY < startY + butH) {
				startTime = millis();
				missed = 0;
				score = 0;
				currentState = GameState.RUNNING;
			}
			// credits button
			else if (mouseX > credX && mouseX < credX + butW && mouseY > credY && mouseY < credY + butH) {
				currentState = GameState.CREDITS;
			}
			// exit button
			else if (mouseX > exitX && mouseX < exitX + butW && mouseY > exitY && mouseY < exitY + butH) {
				System.exit(0); // donezo
			}
			break;

		case RUNNING:
			textBox.pressed(mouseX, mouseY);
			break;

		case GAMEOVER:
			// restart button
			if (mouseX > restartX && mouseX < restartX + butW && mouseY > startY && mouseY < restartY + butH) {
				score = 0;
				startTime = millis();
				playerX = PLAYER_X;
				missed = 0;
				currentState = GameState.RUNNING;
			}
			// credits button
			else if (mouseX > credX && mouseX < credX + butW && mouseY > credY && mouseY < credY + butH) {
				currentState = GameState.CREDITS;
			}
			// exit button
			else if (mouseX > exitX && mouseX < exitX + butW && mouseY > exitY && mouseY < exitY + butH) {
				System.exit(0); // donezo
			}

			break;

		case CREDITS:
			if (mouseX > 0 && mouseX < 0 + 75 && mouseY > 0 && mouseY < 0 + 75) {
				currentState = GameState.MENU;
			}
			break;
		}
	}

	/**
	 * Main Method, calls the PApplet main method to run game.
	 */
	public static void main(String args[]) {
		//Login.main(args);
		PApplet.main(new String[] { horsegame.HorseGame.class.getName() });
	}

////////////////////////////////////////////////////////////////////
//////////  Text Box  //////////////////////////////////////////////
////////////////////////////////////////////////////////////////////

	/**
	 * A text box object that can get and read input from the user as the game runs.
	 * @author Bret Abel based on code by Mitko Nikov
	 *
	 */
	private class TextBox {
		public int X = 0, Y = 0, H = 35, W = 200;
		public int TEXTSIZE = 24;

		// COLORS
		public int Background = color(140, 140, 140);
		public int Foreground = color(0, 0, 0);
		public int BackgroundSelected = color(160, 160, 160);
		public int Border = color(30, 30, 30);

		public boolean BorderEnable = false;
		public int BorderWeight = 1;

		public String Text = "";
		public int TextLength = 0;

		private boolean selected = false;

		TextBox() {
			// default constructor
		}

		TextBox(int x, int y, int w, int h) {
			X = x;
			Y = y;
			W = w;
			H = h;
		}

		void draw() {
			// DRAWING THE BACKGROUND
			if (selected) {
				fill(BackgroundSelected);
			} else {
				fill(Background);
			}

			if (BorderEnable) {
				strokeWeight(BorderWeight);
				stroke(Border);
			} else {
				noStroke();
			}

			rect(X, Y, W, H);

			// DRAWING THE TEXT ITSELF
			fill(Foreground);
			textSize(TEXTSIZE);
			textAlign(LEFT);
			text(Text, X + (textWidth("a") / 2), Y + TEXTSIZE);
		}

		/**
		 * Handles key press events. This version only permits numbers, backspace,
		 * and the enter key.
		 * @param KEY The key that is pressed
		 * @param KEYCODE The particular int mapped to a physical key
		 * @return
		 */
		boolean keyPressed(char KEY, int KEYCODE) {
			if (selected) {
				if (KEYCODE == (int) BACKSPACE && TextLength > 0) {
					BACKSPACE();
				} else if (KEYCODE == (int) ENTER) {
					return true;
				} else {
					// Checks what type key is pressed
					// boolean isUppercase = (KEY >= 'A' && KEY <= 'Z');
					// boolean isLowercase = (KEY >= 'a' && KEY <= 'z');
					boolean isNumber = (KEY >= '0' && KEY <= '9');

					if (isNumber) {
						addText(KEY);
					}
				}
			}

			return false;
		}

		private void clear() {
			this.Text = "";
			this.TextLength = 0;
		}

		private void addText(char text) {
			// IF THE TEXT WIDTH IS IN BOUNDARIES OF THE TEXTBOX
			if (textWidth(Text + text) < W) {
				Text += text;
				TextLength++;
			}
		}

		private void BACKSPACE() {
			if (TextLength - 1 >= 0) {
				Text = Text.substring(0, TextLength - 1);
				TextLength--;
			}
		}

		// Detects mouse over text box
		private boolean overBox(int x, int y) {
			if (x >= X && x <= X + W) {
				if (y >= Y && y <= Y + H) {
					return true;
				}
			}

			return false;
		}

		// detects click on text box
		void pressed(int x, int y) {
			if (overBox(x, y)) {
				selected = true;
			} else {
				selected = false;
			}
		}

		private boolean isEmpty() {
			if(this.Text.equals("")) {
				return true;
			} else return false;
		}
	}
}
