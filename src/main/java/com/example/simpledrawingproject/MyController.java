package com.example.simpledrawingproject;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
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

import java.util.LinkedList;
import java.util.List;

public class MyController {

    private List<Uniterm> listOfUniterms;
    private int startX;
    private int startY;
    private static int sizeOfFont = 14;

    @FXML
    private Pane drawingPane;
    @FXML
    private TextField aExapressionTxtField,bExpressionTxtField;
    @FXML
    private RadioButton przecinekRadioBtn,leftRadioBtn;
    @FXML
    public void initialize(){
        drawingPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        startX = 50;
        startY = 50;
        listOfUniterms = new LinkedList<>();
    }

    @FXML
    public void onClearingBtn()
    {
        drawingPane.getChildren().clear();
        listOfUniterms.clear();
        startX = 50;
        startY = 50;
    }

    @FXML
    public void onExchangeBtn(){
        redrawUniterm();
    }

    private void redrawUniterm() {
       //narysuj sekwencjonowany uniterm
        boolean leftExchange = leftRadioBtn.isSelected();
        Uniterm newUniterm,u1,u2;
        u2 = listOfUniterms.get(listOfUniterms.size() - 1);
        u1 = listOfUniterms.get(listOfUniterms.size() - 2);

        if(leftExchange){
            onClearingBtn();
            newUniterm = new Uniterm(u2.getExpression(),u1.getOperation(),u1.getB(),new Point2D(u1.getStartPoint().getX(),
                    u1.getStartPoint().getY()));
            printExpression(newUniterm, newUniterm.getExpression());
            newUniterm.setStartPoint(new Point2D(u1.getStartPoint().getX(),u1.getStartPoint().getY() - 5));
            drawBezier(newUniterm, (newUniterm.getExpression()).length());
            drawBezier(u1, u2.getExpression().length());
        }
        else{
            onClearingBtn();
            newUniterm = new Uniterm(u1.getA(),u1.getOperation(),u2.getExpression(),new Point2D(u1.getStartPoint().getX(),
                    u1.getStartPoint().getY()));
            printExpression(newUniterm, newUniterm.getExpression());

            newUniterm.setStartPoint(new Point2D(newUniterm.getStartPoint().getX(),newUniterm.getStartPoint().getY() - 5));
            drawBezier(newUniterm,newUniterm.getExpression().length());//long bezier curve
            Point2D startPoint = new Point2D(u1.getStartPoint().getX() +
                    convertLengthToPixels((u1.getA() + " " + u1.getOperation() + " ").length()),u1.getStartPoint().getY());
            u1.setStartPoint(startPoint);
            drawBezier(u1,u2.getExpression().length());//short bezier curve
        }
    }

    public int convertLengthToPixels(int length){
        int pixels = 0;
        if(length <= 10){
            pixels = (6 * length) + 2;
        }
        if(length > 10){
            pixels = 7 * length - 3;
        }
        if(length > 19){
            pixels = (6  * (length + 5)) ;
        }
        if(length > 28){
            pixels = (7  * length) + 20;
        }
        return pixels;
    }

    @FXML
    public void onDrawingBtn(){
        if(drawingPane.getChildren().size() > 0){
            startY += 50;
        }

        String a = aExapressionTxtField.getText();
        String b = bExpressionTxtField.getText();
        char operation = przecinekRadioBtn.isSelected()? ',' : ';' ;
        String expr = a + " " + operation + " " + b;

        Uniterm uniterm = new Uniterm(a,operation,b,new Point2D(startX,startY));

        printExpression(uniterm,expr);
        double endX = drawBezier(uniterm, expr.length());

        Point2D endPoint1 = new Point2D(endX,startY);//if endY would exist it would be equal to startY
        uniterm.setEndPoint(endPoint1);
        listOfUniterms.add(uniterm);
    }

    private void printExpression(Uniterm uniterm,String expression) {
        Text text = new Text(expression);
        text.setFont(Font.font("Arial", sizeOfFont));
        text.setFill(Color.BLACK);
        text.setX(uniterm.getStartPoint().getX());
        text.setY(uniterm.getStartPoint().getY() + sizeOfFont);

        drawingPane.getChildren().add(text);
    }

    @FXML
    public double drawBezier(Uniterm uniterm,int length) {
        Path path = new Path();
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);

        double startX = uniterm.getStartPoint().getX();
        double startY = uniterm.getStartPoint().getY();
        // Start of curve line
        MoveTo moveTo = new MoveTo(startX,startY);
        double endX = 0;

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
            endX = startX + (7  * length) + 15;
        }

        //System.out.println("Length of expression: " + (endX - startX));

        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setControlX(startX + ((endX - startX) / 2));
        quadCurveTo.setControlY(startY - (0.8 * length));
        quadCurveTo.setX(endX);
        quadCurveTo.setY(startY);

        path.getElements().add(moveTo);
        path.getElements().add(quadCurveTo);

        drawingPane.getChildren().add(path);
        return endX;
    }

    @FXML
    public void saveToDatabase(){
        System.out.println("Hello Word !");
    }

}

//@ToDo no.3 podpiąć MySQL Server i zpisywać dane do bazy