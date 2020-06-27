import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

    /** Width of each brick in pixels */
    private static final int BRICK_WIDTH = 30;

    /** Width of each brick in pixels */
    private static final int BRICK_HEIGHT = 12;

    /** Number of bricks in the base of the pyramid */
    private static final int BRICKS_IN_BASE = 14;

    public void run() {
        setBricks();
    }
    private void setBricks()
    {

        for( int i = 0; i < BRICKS_IN_BASE; i++ )
        {
            int bricksInRow = BRICKS_IN_BASE - i;


            for( int j = 0; j < bricksInRow; j++ )
            {
                // calculate every x and y points for brick
                int x = ( getWidth()/2 ) - (BRICK_WIDTH * bricksInRow) / 2 + j * BRICK_WIDTH;

                int y = getHeight() - BRICK_HEIGHT * (i+1);

                GRect brick = new GRect( x , y , BRICK_WIDTH , BRICK_HEIGHT );
                add(brick);
            }
        }
    }
}