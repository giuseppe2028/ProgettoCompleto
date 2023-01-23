module com.example.progettocompleto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires jdk.jfr;


    opens com.example.progettocompleto to javafx.fxml;
    exports com.example.progettocompleto;
    opens com.example.progettocompleto.Contenitori to javafx.fxml;
    exports com.example.progettocompleto.Contenitori;
    opens com.example.progettocompleto.FileDiSistema to javafx.fxml;
    exports com.example.progettocompleto.FileDiSistema;
    opens com.example.progettocompleto.GestioneRichieste.Schermate to javafx.fxml;
    exports com.example.progettocompleto.GestioneRichieste.Schermate;
    opens com.example.progettocompleto.GestioneImpiegati.Schermate to javafx.fxml;
    exports com.example.progettocompleto.GestioneImpiegati.Schermate;
    opens com.example.progettocompleto.GestioneTurni.Schermate to javafx.fxml;
    exports com.example.progettocompleto.GestioneTurni.Schermate;
    opens com.example.progettocompleto.GestioneAutenticazione.Schermate to javafx.fxml;
    exports com.example.progettocompleto.GestioneAutenticazione.Schermate;
    opens com.example.progettocompleto.GestioneProfilo.Schermate to javafx.fxml;
    exports com.example.progettocompleto.GestioneProfilo.Schermate;
    exports com.example.progettocompleto.GestioneRichieste.Control;
    opens com.example.progettocompleto.GestioneRichieste.Control to javafx.fxml;



}