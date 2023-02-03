package com.example.progettocompleto.View.SchermateGestioneRichieste;


import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Controller.ControlGestioneRichieste.ControlGestioneRichieste;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SchermataRichiestaMaternita {
    private File file;
    @FXML
    private DatePicker dataIn;
    @FXML
    private DatePicker dataFi;

    ControlGestioneRichieste controlGestioneRichieste;

    public SchermataRichiestaMaternita(ControlGestioneRichieste controlGestioneRichieste) {
        this.controlGestioneRichieste = controlGestioneRichieste;
    }

    public void initialize() {
        dataIn.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate dateC, boolean empty) {
                super.updateItem(dateC, empty);
                    if ( (dateC.getDayOfWeek() == DayOfWeek.SUNDAY) || dateC.isBefore(LocalDate.now())) {
                        setDisable(true);

                    }
                }

        });
        dataIn.setOnAction(e -> {
            LocalDate selectedDate = dataIn.getValue();
            dataFi.setValue(selectedDate);
            dataFi.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate dateC, boolean empty) {
                    super.updateItem(dateC, empty);
                    LocalDate selectedDate1= selectedDate.plusMonths(5);
                    if ((dateC.isAfter(selectedDate1)  || (dateC.getDayOfWeek() == DayOfWeek.SUNDAY) || dateC.isBefore(LocalDate.now()) || dateC.isBefore(selectedDate))) {
                            setDisable(true);

                        }
                    }

            });
        });
    }

    @FXML
    public void clickCertificato(ActionEvent e) {

        FileChooser fileChooser =new FileChooser();
        file = fileChooser.showOpenDialog(Start.mainStage);

    }

    public void clickInvia(ActionEvent e) throws IOException {
        LocalDate dataInizio = dataIn.getValue();
        LocalDate dataFine = dataFi.getValue();
        FileInputStream inputStream = new FileInputStream(file);
        controlGestioneRichieste.clickInviaMaternita(dataInizio,dataFine,inputStream);

    }
    public void clickIndietro(ActionEvent e)
    {
        Util.ritorno("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml");

    }
}
