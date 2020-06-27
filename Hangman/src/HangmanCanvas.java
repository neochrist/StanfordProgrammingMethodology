/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

import java.awt.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */

	public void reset() {
		removeAll();
		setup();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		GLabel text = new GLabel(word);
		text.setLocation(getWidth() / 2 - BEAM_LENGTH, getHeight() / 2 + SCAFFOLD_HEIGHT / 2 + SCAFFOLD_HEIGHT / 2 - 20);
		add(text);

	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(String word) {
		GLabel text = new GLabel(word);
		text.setLocation(getWidth() / 2 - BEAM_LENGTH, getHeight() / 2 + SCAFFOLD_HEIGHT + SCAFFOLD_HEIGHT / 2 + SCAFFOLD_HEIGHT / 4);
		add(text);
	}

	public void addBody(int number){
		if (number == 7) head();
		if (number == 6) body();
		if (number == 5) leftArm();
		if (number == 4) rightArm();
		if (number == 3) leftFeet();
		if (number == 2) rightFeet();
		if (number == 1) leftFoot();
		if (number == 0) rightFoot();
	}

	private void setup (){
		int x = getWidth() / 2 - BEAM_LENGTH;
		int y = getHeight() / 2 + SCAFFOLD_HEIGHT /2;
		GLine scaffold = new GLine(x, y, x, y - SCAFFOLD_HEIGHT);
		add(scaffold);

		GLine beam = new GLine(x, y - SCAFFOLD_HEIGHT, x + BEAM_LENGTH, y - SCAFFOLD_HEIGHT);
		add(beam);

		GLine rope = new GLine(x + BEAM_LENGTH, y - SCAFFOLD_HEIGHT, x + BEAM_LENGTH, y - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		add(rope);
	}

	private void head(){
		int x = getWidth() / 2 - HEAD_RADIUS / 2;
		int y = getHeight() / 2 + SCAFFOLD_HEIGHT /2 - SCAFFOLD_HEIGHT + ROPE_LENGTH;
		GOval head = new GOval(HEAD_RADIUS, HEAD_RADIUS );
		head.setLocation(x, y);
		add(head);
	}

	private void body () {
		int x = getWidth() / 2;
		int y = getHeight() / 2 + SCAFFOLD_HEIGHT /2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS;
		GLine body = new GLine(x, y, x , y + BEAM_LENGTH);
		add(body);
	}
	private void leftArm(){
		int x = getWidth() / 2;
		int y = getHeight() / 2 + SCAFFOLD_HEIGHT /2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine arm = new GLine(x, y, x - UPPER_ARM_LENGTH, y);
		add(arm);

		GLine lowerArm = new GLine(x - UPPER_ARM_LENGTH, y, x- UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);
		add(lowerArm);
	}
	private void rightArm(){
		int x = getWidth() / 2;
		int y = getHeight() / 2 + SCAFFOLD_HEIGHT /2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine arm = new GLine(x, y, x + UPPER_ARM_LENGTH, y);
		add(arm);
		GLine lowerArm = new GLine(x + UPPER_ARM_LENGTH, y, x + UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);
		add(lowerArm);
	}

	private void leftFeet(){
		int x = getWidth() / 2;
		int y = getHeight() / 2 + SCAFFOLD_HEIGHT / 2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS;

		GLine feet = new GLine(x, y, x - HIP_WIDTH, y);
		add(feet);

		GLine leg = new GLine(x - HIP_WIDTH, y, x -HIP_WIDTH, y + LEG_LENGTH);
		add(leg);

	}

	private void rightFeet(){
		int x = getWidth() / 2;
		int y = getHeight() / 2 + SCAFFOLD_HEIGHT / 2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS;

		GLine feet = new GLine(x, y, x + HIP_WIDTH, y);
		add(feet);

		GLine leg = new GLine(x + HIP_WIDTH, y, x + HIP_WIDTH, y  + LEG_LENGTH);
		add(leg);
	}

	private void leftFoot(){
		int x = getWidth() / 2 - HIP_WIDTH;
		int y = getHeight() / 2 + SCAFFOLD_HEIGHT / 2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS + LEG_LENGTH;

		GLine foot = new GLine(x, y, x -  FOOT_LENGTH, y);
		add(foot);

	}
	private  void rightFoot(){
		int x = getWidth() / 2 + HIP_WIDTH;
		int y = getHeight() / 2 + SCAFFOLD_HEIGHT / 2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + BODY_LENGTH + HEAD_RADIUS + LEG_LENGTH;

		GLine foot = new GLine(x, y, x + FOOT_LENGTH, y );
		add(foot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 54;
	private static final int HEAD_RADIUS = 72;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;



}
