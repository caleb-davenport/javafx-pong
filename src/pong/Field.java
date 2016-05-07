package pong;

import java.util.ArrayList;
import javafx.scene.Group;

/**
 *
 * @author Caleb Davenport
 */
public class Field extends Group {

    private final ArrayList<Ball> ballList = new ArrayList<>();
    public final Paddle leftPaddle = new Paddle(true);
    public final Paddle rightPaddle = new Paddle(false);
    
    Field() {
        addBall();
        super.getChildren().add(rightPaddle);
        super.getChildren().add(leftPaddle);
    }

    public void updateField() {
        for (Ball b : ballList) {
            b.updatePosition();
        }
        rightPaddle.updatePosition();
        leftPaddle.updatePosition();
    }

    public final void addBall() {
        Ball newBall = new Ball();
        ballList.add(newBall);
        super.getChildren().add(newBall);
    }
}
