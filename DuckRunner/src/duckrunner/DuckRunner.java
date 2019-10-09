package duckrunner;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

public class DuckRunner extends PApplet {
	PFont menufont;

	//Images
	PImage spritesheet;
	PImage background;
	PImage obstacle;

	int sheetW = 4;
	int sheetH = 1;
	PImage[] sprites = new PImage[sheetW * sheetH];
	int animationFrame = 1;

	//player coordinates
	int playerX = 60;
	int playerY = 100;

	//background coordinates
	int bgX = 0;
	int bgY = 0;

	//obstacle coordinates
	int obstacleX;
	int obstacleY = 500;

	float speed = 5;

	int jumpHeight;

	int startTime;
	int timer;

	float speedIncrease = .8f;
	float rateOfDifficulty = 5;
	int difficultyTime = 0;

	int score = 0;
	int highScore = 0;

	enum GameState {
		GAMEOVER, RUNNING, MENU, CREDITS
	}

	static GameState currentState;

	// main method calls PApplet main method to run game
	public static void main(String[] args) {
		PApplet.main("duckrunner.DuckRunner");
	}

	// game window size
	public void settings() {
		size(1262, 546);
	}

	public void setup() {
		// divides sprite sheet into multiple images to be used to animate sprite
		spritesheet = loadImage("characterSprite.png");
		int w = (spritesheet.width / 2) / sheetW;
		int h = spritesheet.height / sheetH;
		for (int i = 0; i < sprites.length; i++) {
			int x = i % sheetW * w;
			int y = 0;
			sprites[i] = spritesheet.get(x, y, w, h);
		}
		menufont = createFont("HeartbitXX.ttf", 60);
		background = loadImage("background.png");
		obstacle = loadImage("garbage.png");
		obstacle.resize(75, 0);
		startTime = millis();
		currentState = GameState.MENU;
	}

	public void draw() {
		switch (currentState) {
		case MENU:
			drawMenu();
			break;

		case CREDITS:
			drawCredits();
			break;

		case RUNNING:
			timer = (millis() - startTime) / 1000;
			drawBackground();
			if (frameCount % 5 == 0) {
				animationFrame++;
				animationFrame = animationFrame % sprites.length;
			}

			drawPlayer();
			createObstacles();
			drawScore();
			increaseDifficulty();
			break;
		case GAMEOVER:
			drawGameOver();
			break;
		}
	}

	public void drawMenu() {
		score = 0;
		clear();
		background(0, 0, 0);

		textAlign(CENTER, CENTER);
		textFont(menufont, 100);
		fill(90, 200, 215); // teal
		text("Menu", width / 2, height / 2 - 200);

		textFont(menufont, 60);
		fill(200);
		text("start", width / 2, height / 2 - 20);
		text("credits", width / 2, height / 2 + 20);

		// displays the mouse coordinates on screen
		textFont(menufont, 36);
		text("MouseX: " + mouseX, 100, 10);
		text("MouseY: " + mouseY, 100, 50);
	}

	public void drawCredits() {
		clear();
		background(0);

		textAlign(CENTER, CENTER);
		textFont(menufont, 100);
		fill(90, 200, 215); // teal
		text("credits", width / 2, height / 2 - 250);

		textAlign(LEFT, CENTER);
		textFont(menufont, 60);
		fill(200);
		text("code - trout", width / 4, height / 2 - 200);
		text("sprites - trout", width / 4, height / 2 - 170);
	}

	public void drawPlayer() {
		imageMode(CENTER);
		if (playerY <= 435) {
			jumpHeight += 1;
			playerY += jumpHeight;
		}
		image(sprites[animationFrame], playerX, playerY);
	}

	public void drawBackground() {
		imageMode(CORNER);
		image(background, bgX, bgY);
		image(background, bgX + background.width, bgY);
		bgX -= speed;
		if (bgX <= (background.width * -1)) {
			bgX = 0;
		}
	}

	public void mousePressed() {
		if (currentState == GameState.MENU && mouseX > 590 && mouseX < 670 && mouseY > 250 && mouseY < 275) {
			currentState = GameState.RUNNING;
		}

		if (currentState == GameState.MENU && mouseX > 590 && mouseX < 670 && mouseY > 290 && mouseY < 315) {
			currentState = GameState.CREDITS;
		}

		if (currentState == GameState.RUNNING) {
			if (playerY >= 400) {
				jumpHeight = -15;
				playerY += jumpHeight;
			}
		}
		if (currentState == GameState.GAMEOVER) {
			obstacleX = 0;
			bgX = 0;
			startTime = millis();
			speed = 5;
			currentState = GameState.MENU;
		}
	}

	public void createObstacles() {
		imageMode(CENTER);
		image(obstacle, obstacleX, obstacleY);
		obstacleX -= speed;
		if (obstacleX < 0) {
			obstacleX = width;
		}
		if ((abs(playerX - obstacleX) < 40) && abs(playerY - obstacleY) < 80) {
			score = timer;
			if (score > highScore) {
				highScore = score;
			}
			currentState = GameState.GAMEOVER;
		}
	}

	public void drawScore() {
		fill(255, 255, 255);
		textAlign(CENTER);
		textFont(menufont);
		text("Score: " + timer, width - 90, 30);
	}

	public void increaseDifficulty() {
		if (timer % rateOfDifficulty == 0 && difficultyTime != timer) {
			speed += speedIncrease;
			difficultyTime = timer;
		}
	}

	public void drawGameOver() {
		fill(0);
		stroke(90, 200, 215);
		strokeWeight(5);
		strokeJoin(BEVEL);
		rect(width / 2 - 140, height / 2 - 80, 280, 160);
		fill(200);
		textFont(menufont, 60);
		textAlign(CENTER);
		text("game over ;(", width / 2, height / 2 - 40);
		text("your score " + score, width / 2, height / 2 + 10);
		text("high score: " + highScore, width / 2, height / 2 + 60);
	}
}