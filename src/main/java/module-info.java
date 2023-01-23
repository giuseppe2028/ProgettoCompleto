module com.example.progettocompleto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires jdk.jfr;


    opens com.example.progettocompleto to javafx.fxml;
    exports com.example.progettocompleto;
}