package pong;

import javafx.scene.paint.*;
import javafx.scene.shape.*;
import static pong.Pong.*;

/**
 * 
 * @author Caleb Davenport
 */
public class Ball extends Circle {
    private static final int DEFAULT_SIZE = 10;
    private static final double DEFAULT_SPEED = 5.0;
    private double X, Y, velX, velY;
    Ball(int r, double speed) {
        double[] velocity = startingVelocity(speed);
        X = SCENE_X/2.0;
        Y = SCENE_Y/2.0;
        super.setRadius(r);
        super.setFill(Color.web("#FFF"));
        super.setCenterX(X);
        super.setCenterY(Y);
        
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
        X += velX;
        Y += velY;
        super.setCenterX(X);
        super.setCenterY(Y);
    }
}
