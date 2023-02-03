package com.example.progettocompleto.View.SchermateGestioneRichieste;


import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Controller.ControlGestioneRichieste.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SchermataRichiestaSciopero {
    private ControlGestioneRichieste controlGestioneRichieste;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private TextArea motivazioneText;
    @FXML
    private TextArea svolgimentoText;
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
       String motivazione= motivazioneText.getText();
       String svolgimento= svolgimentoText.getText();
        controlGestioneRichieste.clickInviaSciopero(data, motivazione,svolgimento);


}



}
