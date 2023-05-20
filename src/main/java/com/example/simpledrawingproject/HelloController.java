package com.example.simpledrawingproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        drawDezier();
    }

    public void drawDezier() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyGui.fxml"));
        Parent root = (Parent)loader.load();

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

        root.getChildrenUnmodifiable().add(path);
    }
}