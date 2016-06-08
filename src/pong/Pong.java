package pong;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.*;
import javafx.stage.Stage;

/**
 *
 * @author Caleb Davenport
 */
public class Pong extends Application {

    public static final int SCENE_X = 800;
    public static final int SCENE_Y = 600;
    public static final boolean DEBUG = true;

    @Override
    public void start(Stage primaryStage) {
        Field field = new Field();

        Scene scene = new Scene(field, SCENE_X, SCENE_Y, Color.BLACK);

        primaryStage.setTitle("Pong");
        primaryStage.setScene(scene);

        //final long startNanoTime = System.nanoTime();
        ArrayList<KeyCode> inputList = new ArrayList<>();

        //Default Keys for now
        // UP and DOWN for right paddle
        // Q  and A    for left paddle
        scene.setOnKeyPressed((KeyEvent event) -> {
            KeyCode key = event.getCode();
            if (inputList.contains(key)) {
                return;
            }
            inputList.add(key);
            for (Paddle p : field.paddleList) {
                if (key.equals(p.upKey)) p.moveUp(true);
                if (key == p.downKey)    p.moveDown(true);
            }

            if (DEBUG) System.out.println("PRESSED: " + event.getCode());
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            KeyCode key = event.getCode();
            inputList.remove(key);
            for (Paddle p : field.paddleList) {
                if (key == p.upKey)   p.moveUp(false);
                if (key == p.downKey) p.moveDown(false);
            }

            if (DEBUG) System.out.println("RELEASED: " + event.getCode());
        });
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                field.updateField();
            }
        }.start();

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
