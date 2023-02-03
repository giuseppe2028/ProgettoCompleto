package com.example.progettocompleto.Controller.ControlGestioneImpiegati;



import com.example.progettocompleto.Controller.ControlGestioneAutenticazione.ControlLogin;
import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.View.Popup.PopupConferma;
import com.example.progettocompleto.View.SchermateGestioneAutenticazione.SchermataPrincipaleDatore;
import com.example.progettocompleto.View.SchermateGestioneImpiegati.SchermataFestivitaFerie;

import com.example.progettocompleto.Contenitori.Periodi;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.View.Popup.PopupErrore;
import com.example.progettocompleto.View.Popup.PopupInformazione;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlFestivitaFerie {
    private Stage stagePopup = new Stage();
    private List<Periodi> periodi;
    Stage stage = Start.mainStage;

    public ControlFestivitaFerie() {
        periodi = Daemon.getPeriodi();
        Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataFesitivitaFerie.fxml", stage, c -> new SchermataFestivitaFerie(this, periodi));
    }


    public void clickInvia(LocalDate dataInizio, LocalDate dataFine, String categoria) throws SQLException {
        Stage stagePopup = new Stage();
        if (Daemon.verifyDateProibite(dataInizio, dataFine, categoria)) {
            Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupErrore.fxml", stagePopup, c -> new PopupErrore("le date da lei inserite sono già registrate", stagePopup));
            stagePopup.showAndWait();
            SchermataFestivitaFerie.show(Daemon.getPeriodi());

        } else {
            Daemon.insertDateProibite(dataInizio, dataFine, categoria);
            Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupInformazione.fxml", stagePopup, c -> new PopupInformazione("Richiesta Accettata", stagePopup));
            stagePopup.showAndWait();
            SchermataFestivitaFerie.show(Daemon.getPeriodi());
        }
    }

    public void clickIndietro(String s) {
        SchermataPrincipaleDatore schermataPrincipaleDatore = new SchermataPrincipaleDatore(new ControlLogin(),(ArrayList<Object>) EntityUtente.getDatiProfilo());
        Thread thread = new Thread(schermataPrincipaleDatore);
        thread.start();
        Util.setScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/SchermataPrincipaleDatore.fxml",stage,c-> schermataPrincipaleDatore);
    }
    public void rimuoviPeriodo(int id){
        Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupConferma.fxml",stagePopup,c->new PopupConferma("Confermi di voler rimuovere il periodo?",stagePopup));
        stagePopup.showAndWait();
        Daemon.rimuoviPeriodo(id);
        SchermataFestivitaFerie.show(Daemon.getPeriodi());

    }
}

