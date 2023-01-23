package com.example.progettocompleto.GestioneRichieste.Schermate;


import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SchermataRichiestaSciopero {
    ControlGestioneRichieste controlGestioneRichieste;
    @FXML
    DatePicker dataPicker;
    @FXML
    Label motivazioneL;
    @FXML
    Label svolgimentoL;
    public SchermataRichiestaSciopero(ControlGestioneRichieste controlGestioneRichieste){
        this.controlGestioneRichieste=controlGestioneRichieste;
    }
    public void initialize() {
        dataPicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate dateC, boolean empty) {
                super.updateItem(dateC, empty);
                if ((dateC.getDayOfWeek() == DayOfWeek.SUNDAY) || dateC.isBefore(LocalDate.now())) {
                    setDisable(true);

                }
            }

        });
    }
    public void clickIndietro(ActionEvent e)
    {
        Util.ritorno("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml");
    }

public void clickInvia(ActionEvent e){
       LocalDate data= dataPicker.getValue();
       String motivazione= motivazioneL.getText();
       String svolgimento= svolgimentoL.getText();
        controlGestioneRichieste.clickInviaSciopero(data, motivazione,svolgimento);


}



}
