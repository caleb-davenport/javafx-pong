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
