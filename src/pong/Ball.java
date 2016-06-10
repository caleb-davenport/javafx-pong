/*
 * The MIT License
 *
 * Copyright 2016 Caleb Davenport.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package pong;

import java.util.ArrayList;
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
    private boolean canBounce, contact;
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

    private void increaseSpeed(double factor) {
        velX *= factor;
        velY *= factor;
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

    public void velXFlip(ArrayList<Paddle> paddleList) {
        contact = false;
        for (Paddle p : paddleList) {
            if (p.getBoundsInParent().intersects(super.getBoundsInParent())) {
                if (canBounce) {
                    velX *= -1;
                    increaseSpeed(1.05);
                    canBounce = false;
                }
                contact = true;
            }
        }
        if (!contact) canBounce = true;
    }

    public boolean outOfBounds() {
        return X < -20 || X > SCENE_X + 20;
    }
}
