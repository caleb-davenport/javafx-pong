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

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import static pong.Pong.SCENE_X;

/**
 *
 * @author Caleb Davenport
 */
public class TimerVisual extends Group {

    private final Label textScore;
    private int number;
    private boolean isFinished;
    Timeline timeline = new Timeline();
    VBox VBox = new VBox();

    TimerVisual(int number) {
        this.number = number;
        textScore = new Label(Integer.toString(number));
        textScore.setTextFill(Color.WHITE);
        textScore.setFont(Font.font(100));
        textScore.setTranslateY(300);
        
        textScore.setTextAlignment(TextAlignment.CENTER);
        
        VBox.setAlignment(Pos.CENTER);
        VBox.minWidth(300);
        VBox.setTranslateX(SCENE_X / 2 - 100);
        Rectangle center = new Rectangle(200, 1);
        center.setFill(new Color(0, 0, 0, 0));
        
        VBox.getChildren().addAll(textScore, center);
        super.getChildren().add(VBox);
    }

    public boolean startAnimation() {
        timeline.setCycleCount(number);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
                    deductNumber();
        }));
        timeline.play();
        return true;
    }

    public void deductNumber() {
        number--;
        if (number <= 0) {
            textScore.setText("");
            timeline.stop();
            isFinished = true;
        } else {
            textScore.setText(Integer.toString(number));
            timeline.stop();
            timeline.playFromStart();
        }
    }
    
    public boolean getFinishStatus() {
        return isFinished;
    }
}
