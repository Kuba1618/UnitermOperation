package com.example.simpledrawingproject;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.text.*;
public class MyController {

    private int startX;
    private int startY;
    private static int sizeOfFont = 14;

    @FXML
    private Pane drawingPane;
    @FXML
    private TextField aExapressionTxtField,bExpressionTxtField;
    @FXML
    private RadioButton przecinekRadioBtn;
    @FXML
    public void initialize(){
        drawingPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    @FXML
    public void onDrawingBtn(){
        String a = aExapressionTxtField.getText();
        String b = bExpressionTxtField.getText();
        String operation = przecinekRadioBtn.isSelected()? "," : ";";
        String expr = a + " " + operation + " " + b + " ";

        startX = 50;
        startY = 50;
        drawBezier(startX,startY,6 * expr.length());
        //Must be called after drawBezier
        printExpression(expr);
    }

    private void printExpression(String expression) {
        Text text = new Text(expression);
        text.setFont(Font.font("Arial", sizeOfFont));
        text.setFill(Color.BLACK);
        text.setX(startY);
        text.setY(startY + sizeOfFont);

        drawingPane.getChildren().add(text);

        System.out.println(expression);
    }

    @FXML
    public void drawBezier(int startX,int startY,int length) {
        Path path = new Path();
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);

        // Start of curve line
        MoveTo moveTo = new MoveTo(startX,startY);

        //Continue
        QuadCurveTo quadCurveTo = new QuadCurveTo();

        quadCurveTo.setControlX(startX + (length / 2));
        quadCurveTo.setControlY(startY - 20);

        quadCurveTo.setX(startX + length);
        quadCurveTo.setY(startY);

        path.getElements().add(moveTo);
        path.getElements().add(quadCurveTo);

        drawingPane.getChildren().add(path);
    }


}