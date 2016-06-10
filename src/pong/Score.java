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
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static pong.Pong.SCENE_X;

/**
 * 
 * @author Caleb Davenport
 */
public class Score extends Group {
    private int score;
    private final Label textScore;
    
    Score(boolean isPlayer1) {
        textScore = new Label("0");
        textScore.setTextFill(Color.RED);
        textScore.setFont(Font.font(30));
        textScore.setTranslateY(50);
        if (isPlayer1) textScore.setTranslateX(SCENE_X*0.1);
        else           textScore.setTranslateX(SCENE_X*0.9);
        super.getChildren().add(textScore);
    }
    
    public int getScore() {
        return score;
    }
    
    public void incrementScore() {
        score += 1;
        textScore.setText(Integer.toString(score));
    }
}
