package pong;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
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
    
    @Override
    public void start(Stage primaryStage) {
        Field field = new Field();
        
        Scene scene = new Scene(field, SCENE_X, SCENE_Y, Color.BLACK);
        
        primaryStage.setTitle("Pong");
        primaryStage.setScene(scene);
        
        //final long startNanoTime = System.nanoTime();
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            field.addBall();
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
