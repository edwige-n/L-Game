package com.example.lgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    public Pane LRed;
    public Pane LBlue;
    public Rectangle rectangle;
    public GridPane gridPane;
    public Circle circle;
    public StackPane stackPane;

    @Override
    public void start(Stage LStage) throws IOException {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        LRed = new Pane();
        LBlue = new Pane();

        List<Rectangle>  blueRectangles = new ArrayList<>();
        List<Rectangle> redRectangles = new ArrayList<>();

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                // créer les cases
                rectangle = new Rectangle(100, 100, Color.BLACK);
                // L bleu
                if ((row == 2 && col >= 1) || (row == 1 && col == 3)) {
                  //  rectangle.setFill(Color.BLUE);
                    Rectangle blueRectangle = new Rectangle(100, 100, Color.BLUE);
                    blueRectangles.add(blueRectangle);
                    LBlue.getChildren().add(blueRectangle);
                }
                // L rouge
                if ((row == 1 && col <= 2) || (row == 2 && col == 0)) {
                    rectangle.setFill(Color.RED);
                   // Rectangle redRectangle = new Rectangle(100, 100, Color.RED);
                    LRed.getChildren().add(rectangle);
                }
                if ((row == 0 && col == 0) || (row == 3 && col == 3)) {
                    circle = new Circle(20, Color.WHITE);
                    stackPane = new StackPane(rectangle, circle);
                    stackPane.setMaxSize(rectangle.getWidth(), rectangle.getHeight());
                    gridPane.add(stackPane, col, row);

                } else {
                    gridPane.add(rectangle, col, row);
                }
            }
        }

        gridPane.add(LRed, 0, 0);
        gridPane.add(LBlue, 3, 3);
        LBlue.setOnKeyPressed(this::handleKeyPressed);
        LBlue.setFocusTraversable(true);

        Scene scene = new Scene(gridPane, 450, 450);
        scene.setOnKeyPressed(this::handleKeyPressed);
        LStage.setTitle("L-Game");
        LStage.setScene(scene);
        LStage.show();

        gridPane.requestFocus();

    }
    public void handleKeyPressed(KeyEvent e){
        switch (e.getCode()) {
            case UP:
                moveLBlue(LBlue, 0, -100);
                System.out.println("fleche du haut");
                break;
            case DOWN:
                moveLBlue(LBlue, 0, 100);
                System.out.println("fleche du bas");
                break;
            case LEFT:
                moveLBlue(LBlue, -100, 0);
                System.out.println("fleche à gauche");
                break;
            case RIGHT:
                moveLBlue(LBlue, 100, 0);
                System.out.println("fleche à droite");
        }
    }
    public void moveLBlue(Pane LBlue, double deltaX, double deltaY) {
        //les rectangles qui sont attachés au pane
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), LBlue);
        transition.setByX(deltaX);
        transition.setByY(deltaY);
        transition.play();

    }

    public static void main(String[] args) {
        launch();
    }
}