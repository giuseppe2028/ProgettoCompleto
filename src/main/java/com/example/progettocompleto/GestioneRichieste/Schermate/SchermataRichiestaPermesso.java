package com.example.progettocompleto.GestioneRichieste.Schermate;


import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SchermataRichiestaPermesso {
    private ControlGestioneRichieste controlGestioneRichieste;
    @FXML
    private ChoiceBox<String> oraInizio;
    @FXML
    private ChoiceBox<String> minutoInizio;
    @FXML
    private ChoiceBox<String> oraFine;
    @FXML
    private ChoiceBox<String> minutoFine;
    @FXML
    private DatePicker dataPicker;
    private String[] oraIn = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    private String[] oraFin = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    private String[] minutoIn = {"00", "30"};
    private String[] minutoFin = {"00", "30"};
    public SchermataRichiestaPermesso(ControlGestioneRichieste controlGestioneRichieste) {
        this.controlGestioneRichieste = controlGestioneRichieste;
    }

    public void initialize() {
        oraInizio.getItems().addAll(oraIn);
        minutoInizio.getItems().addAll(minutoIn);
        oraFine.getItems().addAll(oraFin);
        minutoFine.getItems().addAll(minutoFin);
        oraInizio.setOnAction(e -> updateEndHourChoiceBox());
        minutoInizio.setOnAction(e -> updateEndHourChoiceBox());
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
        String startMinute = minutoInizio.getValue();
        if (startHour == null || startMinute == null) {
            return;
        }
        // TODO: 20/01/23  da aggiustare il massimo orario perchè me so confusa 
        int startHourInt = Integer.parseInt(startHour);
        int startMinuteInt = Integer.parseInt(startMinute);
        int maxEndHour = startHourInt + 12;
        System.out.println(maxEndHour);
        if (maxEndHour >= oraFin.length) {
            maxEndHour = oraFin.length;
            System.out.println(maxEndHour);
        }
        ObservableList<String> endMinutes = FXCollections.observableArrayList();
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
        for (int i = startMinuteInt; i <= minutoFin.length; i++) {
            if (i < 10) {
                String minute = "0" + String.valueOf(i);
                endMinutes.add(minute);
            } else {
                String minute = String.valueOf(i);
                endMinutes.add(minute);
            }

        }
        oraFine.setItems(endHours);

        if (!endHours.isEmpty()) {
            oraFine.setValue(endHours.get(0));
        }
        if (!endMinutes.isEmpty()) {
            minutoFine.setValue(endMinutes.get(0));
        }

    }
    public void clickIndietro(ActionEvent e)
    {
        Util.ritorno("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml");
    }
}
//todo prestaci attenzione
/*
    public void clickInvia(ActionEvent e){
LocalDate data= dataPicker.getValue();
String oraIni=  oraInizio.getValue();
String minuIni= minutoInizio.getValue();
String oraFin= oraFine.getValue();
String minutFin= minutoFine.getValue();

        controlGestioneRichieste.clickInviaPermesso(data, oraIni, minuIni, oraFin, minutFin);
    }
 */

