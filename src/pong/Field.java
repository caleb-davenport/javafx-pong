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
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static pong.Pong.SCENE_X;
import static pong.Pong.SCENE_Y;

/**
 *
 * @author Caleb Davenport
 */
public class Field extends Group {

    boolean gameInProgress = false;

    private final ArrayList<Ball> ballList = new ArrayList<>();
    public final ArrayList<Paddle> paddleList = new ArrayList<>();

    Score player1score = new Score(true);
    Score player2score = new Score(false);

    Field() {
        addMiddle();
        addScores();
        addBall();
        addPaddle(true, KeyCode.Q, KeyCode.A);
        addPaddle(false, KeyCode.UP, KeyCode.DOWN);
    }

    public void updateField() {
        if (!gameInProgress) {
            return;
        }
        for (Ball b : ballList) {
            b.velXFlip(paddleList);
            b.updatePosition();
            if (b.outOfBounds(true)) {
                player2score.incrementScore();
                newball(b);
            }
            if (b.outOfBounds(false)) {
                player1score.incrementScore();
                newball(b);
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

    public final void newball(Ball b) {
        ballList.remove(b);
        super.getChildren().remove(b);
        addBall();
    }

    public final void addBall() {
        Ball newBall = new Ball();
        ballList.add(newBall);
        super.getChildren().add(newBall);
    }

    public final void addScores() {
        super.getChildren().addAll(player1score, player2score);
    }

    public void start() {
        gameInProgress = true;
    }
    public final void addMiddle() {
        Rectangle r = new Rectangle();
        r.setX(SCENE_X/2 - 1);
        r.setHeight(SCENE_Y);
        r.setWidth(2);
        r.setFill(Color.web("111"));
        super.getChildren().add(r);
    }
}
