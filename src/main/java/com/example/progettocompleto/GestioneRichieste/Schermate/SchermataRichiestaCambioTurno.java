package com.example.progettocompleto.GestioneRichieste.Schermate;

import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.List;

public class SchermataRichiestaCambioTurno {
    @FXML
    Label turnoOri;
    @FXML
    DatePicker dataOrigine;
    @FXML
    DatePicker dataDestinazione;
    @FXML
    ChoiceBox<String> turno;
    private String[] shift= {"mattina", "pomeriggio"};
int matricola;
    private final List<Object> turni;
    ControlGestioneRichieste controlGestioneRichieste;
public SchermataRichiestaCambioTurno(ControlGestioneRichieste controlGestioneRichieste, List<Object> turni, int matricola){
    this.controlGestioneRichieste=controlGestioneRichieste;
    this.turni=turni;
    this.matricola=matricola;
}
    public void clickIndietro(ActionEvent e)
    {
        Util.ritorno("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml");
    }
    public void initialize() {
    turnoOri.setText("");
        dataOrigine.setOnAction(event -> {
            LocalDate turnoOrigine= dataOrigine.getValue();
            String turno= Daemon.getTurno(turnoOrigine, matricola);
            System.out.println(turno);
            turnoOri.setText(turno);
        });
       turno.getItems().addAll(shift);
    }




public void clickConferma(ActionEvent e){
    LocalDate turnoOrigine= dataOrigine.getValue();
    LocalDate turnoDestinazione= dataDestinazione.getValue();

    String turnoDesiderato= turno.getValue();
    String turnoPrecedente= turnoOri.getText();


    controlGestioneRichieste.clickConferma(turnoOrigine, turnoDestinazione, turnoDesiderato, turnoPrecedente);

}


}
