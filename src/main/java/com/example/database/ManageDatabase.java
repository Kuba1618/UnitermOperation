package com.example.database;

import com.example.simpledrawingproject.Uniterm;
import javafx.geometry.Point2D;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManageDatabase {

    private  static String jdbcUrl = "jdbc:mysql://localhost:3306/masi_db?useSSL=false";
    private static String user = "hbstudent";
    private static String password = "hbstudent";

    public static boolean saveToMySQL(List<Uniterm> uniterms) {

        try {
                System.out.println("Connecting to database: " + jdbcUrl);

                Connection myConn = DriverManager.getConnection(jdbcUrl,user,password);
                //System.out.println("Connection successfull !!!");

                for(Uniterm uniterm : uniterms) {

                    String query = "INSERT INTO `masi_db`.`uniterm` (`projectId`,`unitermId`,`nameOfProject`,`description`," +
                            "`a`,`b`,`operation`,`expression`,`startX`,`startY`,`endX`,`endY`,`length`) " +
                            "VALUES (" + uniterm.getProjectInfo().getProjectId() + ",null,'" + uniterm.getProjectInfo().getProjectTitle() + "','" +
                            uniterm.getProjectInfo().getDescription() + "','" + uniterm.getA() + "','" + uniterm.getB() + "','" +
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

    public static List<Uniterm> readFromMySQL() {
        List<Uniterm> itemList = new ArrayList<>();

        try {

            try (Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/masi_db?useSSL=false",
                    "hbstudent","hbstudent")) {
                Statement smt = myConn.createStatement();

                String q = "SELECT * FROM uniterm";

                ResultSet rs = smt.executeQuery(q);

                if (rs.next()) {
                    do {
                        ProjectInfo projectInfo = new ProjectInfo();
                        projectInfo.setProjectId(rs.getInt(1));
                        projectInfo.setProjectTitle(rs.getString(3));
                        projectInfo.setDescription(rs.getString(4));

                        Uniterm uniterm = new Uniterm();
                        uniterm.setUnitermId(rs.getInt(2));
                        uniterm.setA(rs.getString(5));
                        uniterm.setB(rs.getString(6));
                        uniterm.setOperation((char) rs.getByte(7));
                        uniterm.setExpression(rs.getString(8));
                        uniterm.setStartPoint(new Point2D(rs.getDouble(9),rs.getDouble(10)));
                        uniterm.setEndPoint(new Point2D(rs.getDouble(11),rs.getDouble(12)));
                        uniterm.setLength(rs.getInt(13));
                        uniterm.setProjectInfo(projectInfo);

                        itemList.add(uniterm);
                    } while (rs.next());
                } else {
                    System.out.println("Record Not Found...");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return itemList;
    }

}