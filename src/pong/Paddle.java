package pong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static pong.Pong.*;

/**
 * 
 * @author Caleb Davenport
 */
public class Paddle extends Rectangle {
    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 10;
    private static final int DEFAULT_SPEED = 10;
    private double X, Y, velX, velY;
    private double height, width;
    Paddle(int height, int width, boolean isLeft) {
        if (isLeft) X = SCENE_X * 0.05;
        else        X = SCENE_X * 0.95;
        this.height = height;
        this.width = width;
        Y = (SCENE_Y / 2) - (height / 2);
        super.setX(X);
        super.setY(Y);
        super.setHeight(height);
        super.setWidth(width);
        super.setFill(Color.web("#FFF"));
    }
    Paddle(boolean isLeft) {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, isLeft);
    }
    public void updatePosition() {
        X += velX;
        Y += velY;
        if (Y <= 0) Y = 0;
        if (Y >= SCENE_Y - height) Y = SCENE_Y - height;
        super.setX(X);
        super.setY(Y);
    }
    public void moveUp(boolean start) {
        if (start) velY += -DEFAULT_SPEED;
        else       velY += DEFAULT_SPEED;
    }
    public void moveDown(boolean start) {
        if (start) velY += DEFAULT_SPEED;
        else       velY += -DEFAULT_SPEED;
    }
}
