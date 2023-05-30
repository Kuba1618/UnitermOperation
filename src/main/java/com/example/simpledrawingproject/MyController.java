package com.example.simpledrawingproject;

import com.example.database.ManageDatabase;
import com.example.database.ProjectInfo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
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

import java.util.*;

public class MyController {

    private List<Uniterm> databaseUniterms;
    private List<Uniterm> listOfUniterms;
    private Set<ProjectInfo> projectsInfoSet = new HashSet<>();
    private Set<String> projectsInfoNames = new HashSet<>();
    private int startX;
    private int startY;
    private static int sizeOfFont = 14;
    public static String currentProject;

    @FXML
    private Pane drawingPane;
    @FXML
    private ListView<String> projectsLV;
    @FXML
    private TextField aExapressionTxtField,bExpressionTxtField,nameProjectTxtField,descriptionTxtField;
    @FXML
    private RadioButton przecinekRadioBtn,leftRadioBtn;
    @FXML
    public void initialize(){
        drawingPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        startX = 50;
        startY = 50;
        listOfUniterms = new LinkedList<>();
        databaseUniterms = ManageDatabase.readFromMySQL();
        loadProjectsFromDatabase();
        drawProject();
    }

    public void loadProjectsFromDatabase(){
        projectsInfoNames.clear();
        databaseUniterms.clear();
        databaseUniterms.addAll(ManageDatabase.readFromMySQL());

        for(Uniterm u : databaseUniterms){
            projectsInfoSet.add(u.getProjectInfo());//use Set to draw uniterms
            projectsInfoNames.add(u.getProjectInfo().getProjectTitle());
        }

        projectsLV.getItems().addAll(projectsInfoNames);
    }

    public List<Uniterm> findProjectUniterms(){
        List<Uniterm> unitermList = new ArrayList<>();
        for(Uniterm u1 : databaseUniterms){
            System.out.println(u1.getExpression());
            System.out.println(u1.getProjectInfo().getProjectTitle());
            if(u1.getProjectInfo().getProjectTitle().equals(currentProject)){
                unitermList.add(u1);
            }
        }
        return unitermList;
    }

    @FXML
    public void drawProject(){
        projectsLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentProject = observableValue.getValue();
                
                List<Uniterm> uniterms22 = findProjectUniterms();//global variable -> change it
                drawingPane.getChildren().clear();
                listOfUniterms.clear();

                for(Uniterm u2 : uniterms22){
                    drawUniterm(u2);
                }

            }
        });
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

    public Uniterm loadDataFromForms(){
        String a = aExapressionTxtField.getText();
        String b = bExpressionTxtField.getText();
        char operation = przecinekRadioBtn.isSelected()? ',' : ';' ;

        return new Uniterm(a,operation,b,new Point2D(startX,startY));
    }

    public void drawUniterm(Uniterm uniterm){
        String expr = uniterm.getExpression();
        printExpression(uniterm,expr);
        double endX = /* !!! */ drawBezier(uniterm, expr.length());

        Point2D endPoint1 = new Point2D(endX,startY);//if endY would exist it would be equal to startY
        uniterm.setEndPoint(endPoint1);
        listOfUniterms.add(uniterm);
    }
    @FXML
    public void onDrawingBtn(){
        Uniterm u1 = loadDataFromForms();
        if(drawingPane.getChildren().size() > 0){
            double startYOfLastUniterm= listOfUniterms.get(listOfUniterms.size() - 1).getStartPoint().getY();
            u1.setStartPoint(new Point2D(u1.getStartPoint().getX(),startYOfLastUniterm + 50));
        }
        drawUniterm(u1);
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
        Random generator = new Random();
        ProjectInfo projectInfo =
                new ProjectInfo(generator.nextInt(1000) + 100,nameProjectTxtField.getText(),descriptionTxtField.getText());
        for(Uniterm u : listOfUniterms){
            u.setProjectInfo(projectInfo);
        }
        ManageDatabase.saveToMySQL(listOfUniterms);


        refreshProjectsLV();
    }

    public void refreshProjectsLV(){
        projectsLV.getItems().clear();
        loadProjectsFromDatabase();
    }



}