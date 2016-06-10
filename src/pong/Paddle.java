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
        paddle.setFill(Color.web("#FFF5"));
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
