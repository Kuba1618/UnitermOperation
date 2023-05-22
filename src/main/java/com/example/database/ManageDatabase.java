package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ManageDatabase {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/masi_db?useSSL=false";
        String user = "hbstudent";
        String password = "hbstudent";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection myConn = DriverManager.getConnection(jdbcUrl,user,password);
            System.out.println("Connection successfull !!!");

            //@ToDo zrobić zapis do bazy (zmienić wartości w linii 23 na parametry zczytywane z obiektów
            String query = "INSERT INTO `masi_db`.`uniterm`(`projectId`,`unitermId`,`nameOfProject`,`description`,`a`,`b`,`operation`,`expression`,`startX`,`startY`,`endX`,`endY`,`length`) " +
                    "VALUES(,<{unitermId: }>,<{nameOfProject: }>,<{description: }>,<{a: }>,<{b: }>,<{operation: }>,<{expression: }>,<{startX: }>,<{startY: }>,<{endX: }>,<{endY: }>,<{length: }>);";
            Statement statement = myConn.createStatement();
            statement.executeQuery(query);
        }catch(Exception exc){
            exc.printStackTrace();
        }

    }

}