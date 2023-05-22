package com.example.simpledrawingproject;

import javafx.geometry.Point2D;

public class Uniterm {
    private String a;
    private String b;
    private char operation;
    private String expression;
    private Point2D startPoint;
    private Point2D endPoint;
    private int length;

    public Uniterm(String a, char operation, String b, Point2D startPoint) {
        this.a = a;
        this.operation = operation;
        this.b = b;
        this.expression = a + " " + operation + " " + b;
        this.startPoint = startPoint;
        //@ToDo czy wstawić tu zawartość funkcji convertLengthToInt() ?  --> do przemyślenia
        this.length = (this.expression).length();
    }

    public String getExpression() {
        return a + " " + operation + " " + b;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point2D endPoint) {
        this.endPoint = endPoint;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


}
