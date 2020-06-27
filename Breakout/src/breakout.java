/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 *
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class breakout extends GraphicsProgram {

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static final int NTURNS = 3;

    private static final int DELAY = 60;


    /* Method: run() */
    /** Runs the Breakout program. */
    public void run() {
        int life = NTURNS;

        // plays the game till NTURN becomes zero, or player wins

        for (int i = 0; i < NTURNS; i++) {
            boolean win = false;

            setup();
            getVelocity();
            
            while (true) {
                ballMove();
                checkForCollision();
                getCollidingObject();
                if (ball.getY() > paddle.getY()){
                    removeAll();
                    life --;
                    break;
                }
                else if (count == NBRICK_ROWS*NBRICKS_PER_ROW){
                    win = true;
                    break;
                }
                pause(DELAY - 20);
            }

            if (win == true){
                add(winner);
                break;
            }
            else if(life == 0){
                add(lose);
                break;
            }
        }

    }
    //sets up the game
    private void setup(){
        addBricks();
        addBall();
        addPaddle();
    }

    //adds brick
    private void addBricks(){

        for (int i = 0; i < NBRICK_ROWS; i++){
            for (int j = 0; j < NBRICKS_PER_ROW; j ++) {
                BrickSetup(4 + 4 * j + BRICK_WIDTH * j, 70 + 4 * i + BRICK_HEIGHT * i, i);
            }
        }
    }


    // sets up Brick
    private void BrickSetup(double x, double y, int i){
        brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setLocation(x, y);
        if (i > - 1 && i < 2){
            brick.setFillColor(Color.red);
        }
        else if(i > 1 && i < 4){
            brick.setFillColor(Color.orange);
        }
        else if( i > 3 && i < 6){
            brick.setFillColor(Color.yellow);
        }
        else if(i > 5 && i < 8 ){
            brick.setFillColor(Color.green);
        }
        else{
            brick.setFillColor(Color.cyan);
        }
        add(brick);
    }

    // adds paddle
    private void addPaddle(){
        paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setLocation(getWidth() / 2 - PADDLE_WIDTH/2, getHeight() - 30);
        add(paddle);
        addMouseListeners();
    }

    // ball movement for moving mouse, without pressing or any other activity
    public void mouseMoved(MouseEvent e) {

        if ((e.getX() < getWidth() - PADDLE_WIDTH / 2) && (e.getX() > PADDLE_WIDTH / 2)) {
            paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }

    }

    //adds ball
    private void addBall(){
        ball = new GOval(BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        ball.setLocation(getWidth()/2 - BALL_RADIUS, getHeight() / 2 - BALL_RADIUS);
        add(ball);
    }

    // velocity for ball
    private void getVelocity(){
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) vx = -vx;

        vy =  4.0;
    }

    // moving parameters for ball

    private void ballMove(){

        ball.move(vx, vy);

        if (ball.getX() > getWidth() - BALL_RADIUS){
            vx = -vx;
        }
        else if(ball.getX() < BALL_RADIUS){
            vx = -vx;
        }
        else if(ball.getY() > getHeight() - BALL_RADIUS){
            vy = -vy;
        }
        else if(ball.getY() < BALL_RADIUS){
            vy = -vy;
        }
    }

    // checks if ball collided with paddle

    private void checkForCollision(){
        GObject collider = getCollidingObject();
        if (collider == paddle){
            vy = - vy;
        }
    }

    // returns the object ball collided

    private GObject getCollidingObject () {
        GObject ballObj = getElementAt(ball.getX(), ball.getY());
        if (ballObj != null && ballObj != paddle) {
            count ++;
            vy = - vy;
            remove (ballObj);
        }
        ballObj = getElementAt(ball.getX() + BALL_RADIUS*2, getY());

        if (ballObj != null && ballObj != paddle){
            count ++;
            vy = - vy;
            remove(ballObj);
        }
        ballObj = getElementAt(ball.getX(), getY() + 2*BALL_RADIUS);
        if (ballObj != null && ballObj != paddle){
            count ++;
            vx = -vx;
            remove(ballObj);
        }
        ballObj = getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS);
        if (ballObj != null && ballObj != paddle){
            count ++;
            vx = -vx;
            remove(ballObj);
        }
        return ballObj;
    }


    // instance variables
    private int count = 0;
    private GLabel lose = new GLabel("You Have lost!");
    private GLabel winner = new GLabel("You have won!");
    private GRect brick;
    private GRect paddle;
    private GOval ball;
    private double vx, vy;
    private RandomGenerator rgen = RandomGenerator.getInstance();

}