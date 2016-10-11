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
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import static pong.Pong.*;

/**
 *
 * @author Caleb Davenport
 */
public class Ball extends Group {

    private static final int DEFAULT_SIZE = 10;
    private static final double DEFAULT_SPEED = 5.0;
    private double XPosition, YPosition, XVelocity, YVelocity;
    private boolean canBounce;
    private final Circle ball = new Circle();
    private final Rectangle collisionRepresentation = new Rectangle();

    Ball(int r, double speed) {
        setStartingVelocity(speed);
        XPosition = SCENE_X / 2.0;
        YPosition = SCENE_Y / 2.0;
        ball.setRadius(r);
        ball.setFill(Color.web("#FFF"));
        super.setTranslateX(XPosition);
        super.setTranslateY(YPosition);
        if (DEBUG) {
            collisionRepresentation.setHeight(r * 2);
            collisionRepresentation.setWidth(r * 2);
            collisionRepresentation.setX(-r);
            collisionRepresentation.setY(-r);
            collisionRepresentation.setStroke(Color.GREEN);
            collisionRepresentation.setStrokeWidth(2);
            super.getChildren().add(collisionRepresentation);
        }
        super.getChildren().add(ball);
    }

    Ball() {
        this(DEFAULT_SIZE, DEFAULT_SPEED);
    }

    private void setStartingVelocity(double speed) {
        double rawX, rawY, unitX, unitY;
        rawX = 0.25;
        rawY = Math.random() - 0.5;
        unitX = rawX / Math.sqrt(Math.pow(rawX, 2) + Math.pow(rawY, 2));
        unitY = rawY / Math.sqrt(Math.pow(rawX, 2) + Math.pow(rawY, 2));
        XVelocity = speed * unitX;
        YVelocity = speed * unitY;
        if (Math.random() > 0.5) XVelocity *= -1;
    }

    private void increaseSpeed(double factor) {
        XVelocity *= factor;
        YVelocity *= factor;
    }
    
    private boolean contactWithPaddle(Paddle p) {
        Bounds paddleBounds = p.getBoundsInParent();
        Bounds ballBounds =   super.getBoundsInParent();
        return paddleBounds.intersects(ballBounds);
    }

    public final void updatePosition() {
        if (YPosition <= 0 + ball.getRadius() || YPosition >= SCENE_Y - ball.getRadius()) {
            YVelocity *= -1;
        }
        XPosition += XVelocity;
        YPosition += YVelocity;
        super.setTranslateX(XPosition);
        super.setTranslateY(YPosition);
    }

    public void velXFlip(ArrayList<Paddle> paddleList) {
        boolean contactWithPaddle = false;
        for (Paddle p : paddleList) {
            if (contactWithPaddle(p)) {
                contactWithPaddle = true;
                if (canBounce) {
                    XVelocity *= -1;
                    increaseSpeed(1.05);
                    canBounce = false;
                }
            }
        }
        if (!contactWithPaddle) canBounce = true;
    }

    public boolean outOfBounds(boolean isLeft) {
        if (isLeft) return XPosition < -20;
        else return XPosition > SCENE_X + 20;
    }
}
