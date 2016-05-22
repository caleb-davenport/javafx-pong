package pong;

import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import static pong.Pong.*;

/**
 *
 * @author Caleb Davenport
 */
public class Ball extends Group {

    private static final int DEFAULT_SIZE = 10;
    private static final double DEFAULT_SPEED = 5.0;
    private double X, Y, velX, velY;
    private final Circle ball = new Circle();
    public final Rectangle collision = new Rectangle();

    Ball(int r, double speed) {
        double[] velocity = startingVelocity(speed);
        X = SCENE_X / 2.0;
        Y = SCENE_Y / 2.0;
        ball.setRadius(r);
        ball.setFill(Color.web("#FFF"));
        super.setTranslateX(X);
        super.setTranslateY(Y);
        collision.setFill(new Color(1, 1, 1, 0));
        if (DEBUG) {
            collision.setHeight(r * 2);
            collision.setWidth(r * 2);
            collision.setX(-r);
            collision.setY(-r);
            collision.setStroke(Color.GREEN);
            collision.setStrokeWidth(2);
            super.getChildren().add(collision);
        }
        super.getChildren().add(ball);

        velX = velocity[0];
        velY = velocity[1];
    }

    Ball() {
        this(DEFAULT_SIZE, DEFAULT_SPEED);
    }

    private double[] startingVelocity(double speed) {
        final double rawX = 0.25;
        double rawY, unitX, unitY, X, Y;
        double[] velocity = new double[2];
        rawY = Math.random() - 0.5;
        unitX = rawX / Math.sqrt(Math.pow(rawX, 2) + Math.pow(rawY, 2));
        unitY = rawY / Math.sqrt(Math.pow(rawX, 2) + Math.pow(rawY, 2));
        X = speed * unitX;
        Y = speed * unitY;
        if (Math.random() > 0.5) {
            X *= -1;
        }
        velocity[0] = X;
        velocity[1] = Y;
        return velocity;
    }

    public void updatePosition() {
        if (Y <= 0 + ball.getRadius() || Y >= SCENE_Y - ball.getRadius()) {
            velY *= -1;
        }
        X += velX;
        Y += velY;
        super.setTranslateX(X);
        super.setTranslateY(Y);
    }

    public void velXFlip(Paddle paddle) {
        if (paddle.intersects(super.getBoundsInParent())) velX *= -1;
    }
    
    public boolean outOfBounds() {
        return X < -20 || X > SCENE_X + 20;
    }
}
