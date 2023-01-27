package com.example.progettocompleto.GestioneRichieste.Schermate;


import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Control.ControlGestioneRichieste;
import com.example.progettocompleto.Start;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SchermataRichiestaPermesso {
    private ControlGestioneRichieste controlGestioneRichieste;
    @FXML
    ChoiceBox<String> oraInizio;

    @FXML
    ChoiceBox<String> oraFine;

    @FXML
    DatePicker dataPicker;
    private String[] oraIn = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};


    private String[] oraFin = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};


    public SchermataRichiestaPermesso(ControlGestioneRichieste controlGestioneRichieste) {
        this.controlGestioneRichieste = controlGestioneRichieste;


    }

    public void initialize() {
        oraInizio.getItems().addAll(oraIn);
        oraFine.getItems().addAll(oraFin);
        oraInizio.setOnAction(e -> updateEndHourChoiceBox());
        updateEndHourChoiceBox();
        dataPicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate dateC, boolean empty) {
                super.updateItem(dateC, empty);
                if ((dateC.getDayOfWeek() == DayOfWeek.SUNDAY) || dateC.isBefore(LocalDate.now())) {
                    setDisable(true);
                    return;
                }
            }
        });
    }


    private void updateEndHourChoiceBox() {
        String startHour = oraInizio.getValue();
        if (startHour == null) {
            return;
        }
        int startHourInt = Integer.parseInt(startHour);
        int maxEndHour = startHourInt + 12;
        System.out.println(maxEndHour);
        if (maxEndHour >= oraFin.length) {
            maxEndHour = oraFin.length;

        }

        ObservableList<String> endHours = FXCollections.observableArrayList();
        for (int i = startHourInt + 1; i <= maxEndHour; i++) {

            if (i < 10) {
                String hour = "0" + String.valueOf(i);
                endHours.add(hour);
            } else {
                String hour = String.valueOf(i);
                endHours.add(hour);
            }



        }
        oraFine.setItems(endHours);

        if (!endHours.isEmpty()) {
            oraFine.setValue(endHours.get(0));
        }

    }

    public void clickIndietro(ActionEvent e) {

        Util.ritorno("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml");
    }


    public void clickInvia(ActionEvent e) throws SQLException {
        LocalDate data = dataPicker.getValue();
        String oraIni = oraInizio.getValue();

        String oraFin = oraFine.getValue();


        controlGestioneRichieste.clickInviaPermesso(data, oraIni, oraFin);
    }
public static void show(){
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiestaPermesso.fxml", Start.mainStage,c->new SchermataRichiestaPermesso(new ControlGestioneRichieste()));
}

}