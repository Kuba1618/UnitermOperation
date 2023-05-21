package com.example.simpledrawingproject;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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

    private int startX = 20;
    private int startY = 30;
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
    public void onClearingBtn()
    {
        drawingPane.getChildren().clear();
        startX = 20;
        startY = 30;
    }

    @FXML
    public void onDrawingBtn(){
        if(drawingPane.getChildren().size() > 0){
            startY += 50;
        }

        String a = aExapressionTxtField.getText();
        String b = bExpressionTxtField.getText();
        String operation = przecinekRadioBtn.isSelected()? "," : ";";
        String expr = a + " " + operation + " " + b;

        printExpression(expr);
        drawBezier(startX,startY, expr.length());
    }

    private void printExpression(String expression) {
        Text text = new Text(expression);
        text.setFont(Font.font("Arial", sizeOfFont));
        text.setFill(Color.BLACK);
        text.setX(startX);
        text.setY(startY + sizeOfFont);

        drawingPane.getChildren().add(text);

        /*Canvas canvas = new Canvas(460, 310);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFont(new Font("Arial", 14));
        gc.fillText(expression, startX, startY + sizeOfFont);
        drawingPane.getChildren().add(canvas);*/
    }

    @FXML
    public void drawBezier(int startX,int startY,int length) {
        Path path = new Path();
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);

        // Start of curve line
        MoveTo moveTo = new MoveTo(startX,startY);

        double endX = 0;//@ToDo no.1 think about adjust length of bezier line to the text length

        System.out.println(length);
        if(length <= 10){
            endX = startX + (6 * length) + 2;
        }
        if(length > 10){
            endX = startX + 7 * length - 3;
        }
        if(length > 19){
            endX = startX + (6  * (length + 5)) ;
        }
        if(length > 28){
            endX = startX + (7  * length) + 20;
        }
        System.out.println("Length of expression: " + (endX - startX));//1 - |module in Point(x,y)| 3 - (" " + "," + " ")
        //Continue
        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setControlX(startX + ((endX - startX) / 2));
        quadCurveTo.setControlY(startY - (0.075 * endX));
        quadCurveTo.setX(endX);
        quadCurveTo.setY(startY);

        path.getElements().add(moveTo);
        path.getElements().add(quadCurveTo);

        drawingPane.getChildren().add(path);
    }

}

//@ToDo no.2 zrobić sekwencjonowanie
//@ToDo no.3 podpiąć MySQL Server i zpisywać dane do bazy