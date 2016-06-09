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
    public static final boolean DEBUG = false;
    Stage primaryStage = new Stage();
    Field field = new Field();
    Menu menu = new Menu();
    Scene gameScene = new Scene(field, SCENE_X, SCENE_Y, Color.BLACK);
    Scene menuScene = new Scene(menu, SCENE_X, SCENE_Y, Color.BLACK);

    @Override
    public void start(Stage primaryStage) {
        primaryStage = this.primaryStage;

        primaryStage.setTitle("Pong");
        primaryStage.setScene(menuScene);

        //final long startNanoTime = System.nanoTime();
        ArrayList<KeyCode> inputList = new ArrayList<>();

        //Default Keys for now
        // UP and DOWN for right paddle
        // Q  and A    for left paddle
        gameScene.setOnKeyPressed((KeyEvent event) -> {
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
        gameScene.setOnKeyReleased((KeyEvent event) -> {
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
                if (menu.updateMenu()) menuToGame();
            }
        }.start();

        primaryStage.show();
    }
    
    public void menuToGame() {
        primaryStage.setScene(gameScene);
        field.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
