module com.example.simpledrawingproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.simpledrawingproject to javafx.fxml;
    exports com.example.simpledrawingproject;
}