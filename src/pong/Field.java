package pong;

import java.util.ArrayList;
import javafx.scene.Group;

/**
 *
 * @author Caleb Davenport
 */
public class Field extends Group {

    private final ArrayList<Ball> ballList = new ArrayList<>();

    Field() {
        addBall();
    }

    public void updateField() {
        for (Ball b : ballList) {
            b.updatePosition();
        }
    }

    public final void addBall() {
        Ball newBall = new Ball();
        ballList.add(newBall);
        super.getChildren().add(newBall);
    }
}
