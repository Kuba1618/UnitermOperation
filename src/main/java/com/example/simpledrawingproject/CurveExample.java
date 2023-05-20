package com.example.simpledrawingproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.stage.Stage;

public class CurveExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        Path path = new Path();
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);

        // Początek krzywej
        MoveTo moveTo = new MoveTo(50, 100);

        // Definicja krzywej Bezier za pomocą punktów kontrolnych
        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setControlX(150);
        quadCurveTo.setControlY(50);
        quadCurveTo.setX(250);
        quadCurveTo.setY(100);

        // Dodanie elementów do ścieżki
        path.getElements().add(moveTo);
        path.getElements().add(quadCurveTo);

        root.getChildren().add(path);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
