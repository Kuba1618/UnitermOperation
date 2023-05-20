package com.example.simpledrawingproject;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;

import java.io.IOException;

public class MyController {

    @FXML
    private Pane drawingPane; // Referencja do elementu Pane na scenie

    @FXML
    public void initialize() {
        Path path = new Path();
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);

        // Początek krzywej
        MoveTo moveTo = new MoveTo(50, 50);

        // Definicja krzywej Bezier za pomocą punktów kontrolnych
        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setControlX(150);
        quadCurveTo.setControlY(200);
        quadCurveTo.setX(250);
        quadCurveTo.setY(50);

        // Dodanie elementów do ścieżki
        path.getElements().add(moveTo);
        path.getElements().add(quadCurveTo);

        drawingPane.getChildren().add(path);
    }
}