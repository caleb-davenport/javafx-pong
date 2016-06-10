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

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import static pong.Pong.*;

/**
 *
 * @author Caleb Davenport
 */
public class Menu extends Group {
    boolean startGame = false;
    private final Label start;

    Menu() {
        start = new Label("Start");
        start.setTextFill(Color.BLACK);
        start.setFont(Font.font(30));
        start.setTextAlignment(TextAlignment.CENTER);
        start.setTranslateX(SCENE_X/2);
        start.setTranslateY(SCENE_Y/2 - 25);
        
        start.setMouseTransparent(true);
        Rectangle r = new Rectangle(SCENE_X/2 - 100, SCENE_Y/2 - 25, 200, 50);
        r.setFill(Color.WHITE);
        super.getChildren().addAll(r, start);
        MouseHandler h = new MouseHandler();
        r.setOnMousePressed(h);
    }
    class MouseHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            System.out.println(e);
            System.out.println("hello");
            startGame = true;
        }
    }
    public boolean updateMenu() {
        if (startGame) return true;
        
        return false;
    }
}
