package pong;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Caleb Davenport
 */
public class Field extends Group {
    boolean gameInProgress = false;

    private final ArrayList<Ball> ballList = new ArrayList<>();
    public final ArrayList<Paddle> paddleList = new ArrayList<>();

    Field() {
        addBall();
        addPaddle(true, KeyCode.Q, KeyCode.A);
        addPaddle(false, KeyCode.UP, KeyCode.DOWN);
    }

    public void updateField() {
        if (!gameInProgress) return;
        for (Ball b : ballList) {
            b.velXFlip(paddleList);
            b.updatePosition();
            if (b.outOfBounds()) {
                ballList.remove(b);
                super.getChildren().remove(b);
                addBall();
            }
        }
        for (Paddle p : paddleList) {
            p.updatePosition();
        }
    }

    public final void addPaddle(boolean isLeft, KeyCode up, KeyCode down) {
        Paddle p = new Paddle(isLeft);
        p.upKey = up;
        p.downKey = down;
        paddleList.add(p);
        super.getChildren().add(p);
    }
    
    public final void addBall() {
        Ball newBall = new Ball();
        ballList.add(newBall);
        super.getChildren().add(newBall);
    }
    public void start() {
        gameInProgress = true;
    }
}
