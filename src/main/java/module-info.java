module org.example.soft_home_assig3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.soft_home_assig3 to javafx.fxml;
    exports org.example.soft_home_assig3;
}