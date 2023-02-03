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

    opens com.example.progettocompleto.Controller.ControlGestioneAutenticazione to javafx.fxml;
    exports com.example.progettocompleto.Controller.ControlGestioneAutenticazione;
    opens com.example.progettocompleto.Controller.ControlGestioneProfilo to javafx.fxml;
    exports com.example.progettocompleto.Controller.ControlGestioneProfilo;
    opens com.example.progettocompleto.Controller.ControlGestioneRichieste to javafx.fxml;
    exports com.example.progettocompleto.Controller.ControlGestioneRichieste;
    opens com.example.progettocompleto.Controller.ControlGestioneImpiegati to javafx.fxml;
    exports com.example.progettocompleto.Controller.ControlGestioneImpiegati;
    opens com.example.progettocompleto.Controller.ControlGestioneTurni to javafx.fxml;
    exports com.example.progettocompleto.Controller.ControlGestioneTurni;

    opens com.example.progettocompleto.View.SchermateGestioneAutenticazione to javafx.fxml;
    exports com.example.progettocompleto.View.SchermateGestioneAutenticazione;

    opens com.example.progettocompleto.View.SchermateGestioneProfilo to javafx.fxml;
    exports com.example.progettocompleto.View.SchermateGestioneProfilo;

    opens com.example.progettocompleto.View.SchermateGestioneRichieste to javafx.fxml;
    exports com.example.progettocompleto.View.SchermateGestioneRichieste;

    opens com.example.progettocompleto.View.SchermateGestioneImpiegati to javafx.fxml;
    exports com.example.progettocompleto.View.SchermateGestioneImpiegati;

    opens com.example.progettocompleto.View.SchermateGestioneTurni to javafx.fxml;
    exports com.example.progettocompleto.View.SchermateGestioneTurni;
    exports com.example.progettocompleto.View.Popup;



    opens com.example.progettocompleto.View.Popup to  javafx.fxml;

    exports com.example.progettocompleto.Model;
    opens com.example.progettocompleto.Model to javafx.fxml;
    exports com.example.progettocompleto.CodiceFiscale;
    opens com.example.progettocompleto.CodiceFiscale to javafx.fxml;
    exports com.example.progettocompleto.Timer;
    opens com.example.progettocompleto.Timer to javafx.fxml;
    exports com.example.progettocompleto.Mail;
    opens com.example.progettocompleto.Mail to javafx.fxml;





}