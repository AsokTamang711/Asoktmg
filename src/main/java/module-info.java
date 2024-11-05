module com.example.lab_ems {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.lab_ems to javafx.fxml;
    exports com.example.lab_ems;
}