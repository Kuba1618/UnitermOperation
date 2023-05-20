package com.example.simpledrawingproject;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
public class MyController {

    @FXML
    private Pane drawingPane;

    @FXML
    public void onDrawingBtn(){
        drawBezier();
    }

    @FXML
    public void drawBezier() {
        Path path = new Path();
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);

        // Start of curve line
        MoveTo moveTo = new MoveTo(0, 0);

        //Continue
        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setControlX(150);
        quadCurveTo.setControlY(200);
        quadCurveTo.setX(250);
        quadCurveTo.setY(50);

        path.getElements().add(moveTo);
        path.getElements().add(quadCurveTo);

        drawingPane.getChildren().add(path);
    }


}