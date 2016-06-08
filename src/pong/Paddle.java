package pong;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static pong.Pong.*;

/**
 *
 * @author Caleb Davenport
 */
public class Paddle extends Group {

    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 10;
    private static final int DEFAULT_SPEED = 10;
    private double X, Y, velX, velY;
    private double height, width;
    public boolean isLeft;
    public KeyCode upKey, downKey;
    private final Rectangle paddle = new Rectangle();

    Paddle(int height, int width, boolean isLeft) {
        this.height = height;
        this.width = width;
        if (isLeft) X = SCENE_X * 0.05 - width;
        else        X = SCENE_X * 0.95;
        Y = (SCENE_Y / 2) - (height / 2);
        super.setTranslateX(X);
        super.setTranslateY(Y);
        paddle.setHeight(height);
        paddle.setWidth(width);
        paddle.setFill(Color.web("#FFF2"));
        if (DEBUG) {
            paddle.setStroke(Color.GREEN);
            paddle.setStrokeWidth(2);
        }
        super.getChildren().add(paddle);
    }

    Paddle(boolean isLeft) {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, isLeft);
    }

    public void updatePosition() {
        X += velX;
        Y += velY;
        if (Y <= 0) Y = 0;
        if (Y >= SCENE_Y - height) Y = SCENE_Y - height;
        super.setTranslateX(X);
        super.setTranslateY(Y);
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
