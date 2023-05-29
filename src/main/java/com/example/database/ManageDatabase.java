package com.example.database;

import com.example.simpledrawingproject.Uniterm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManageDatabase {

    private  static String jdbcUrl = "jdbc:mysql://localhost:3306/masi_db?useSSL=false";
    private static String user = "hbstudent";
    private static String password = "hbstudent";

    public static boolean saveToMySQL(ProjectInfo projInfo,List<Uniterm> uniterms) {

        try {
                System.out.println("Connecting to database: " + jdbcUrl);

                Connection myConn = DriverManager.getConnection(jdbcUrl,user,password);
                //System.out.println("Connection successfull !!!");

                for(Uniterm uniterm : uniterms) {

                    String query = "INSERT INTO `masi_db`.`uniterm` (`projectId`,`unitermId`,`nameOfProject`,`description`," +
                            "`a`,`b`,`operation`,`expression`,`startX`,`startY`,`endX`,`endY`,`length`) " +
                            "VALUES (" + projInfo.getProjectId() + ",null,'" + projInfo.getProjectTitle() + "','" +
                            projInfo.getDescription() + "','" + uniterm.getA() + "','" + uniterm.getB() + "','" +
                            uniterm.getOperation() + "','" + uniterm.getExpression() + "','" + uniterm.getStartPoint().getX() + "','" +
                            uniterm.getStartPoint().getY() + "','" + uniterm.getEndPoint().getX() + "','" +
                            uniterm.getEndPoint().getY() + "'," + uniterm.getLength() + ");";
                    Statement statement = myConn.createStatement();
                    statement.executeUpdate(query);

                }
                return true;

        } catch(Exception exc){
            exc.printStackTrace();
            return false;
        }
    }

    public List<Uniterm> readFromMySQL(){
        return new ArrayList<>();
    }

}