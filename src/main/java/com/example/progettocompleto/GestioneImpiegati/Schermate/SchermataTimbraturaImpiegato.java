package com.example.progettocompleto.GestioneImpiegati.Schermate;


import com.example.progettocompleto.GestioneImpiegati.Control.ControlGestioneImpiegati;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class SchermataTimbraturaImpiegato {
    @FXML
    DatePicker datePicker;
    @FXML
    ChoiceBox<String> ore;
    @FXML
    ChoiceBox<String> minuti;
    private String[] ora = {"08", "14"};
    private String[] minuto = {"00", "05", "10"};
    ControlGestioneImpiegati controlGestioneImpiegati;
    int matricola;
    public SchermataTimbraturaImpiegato(ControlGestioneImpiegati controlGestioneImpiegati, int matricola) {
        this.controlGestioneImpiegati=controlGestioneImpiegati;
        this.matricola=matricola;
    }

    public void initialize(){
        ore.getItems().addAll(ora);
        minuti.getItems().addAll(minuto);
        datePicker.setDayCellFactory(picker -> new DateCell() {
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
    public LocalDate compila(){
        LocalDate dataArrivo= datePicker.getValue();
        return dataArrivo;
    }
    public LocalTime compila2(){
        //   LocalTime[] orario = new LocalTime[2];
        LocalTime orario = LocalTime.parse(ore.getValue());
        //orario[1] = LocalTime.parse(minuti.getValue());
        return orario;
    }



    public void clickConferma() throws SQLException {
        LocalDate data=compila();
        LocalTime orario=compila2();
        controlGestioneImpiegati.clickConferma(data, orario, matricola);

    }
    public void clickIndietro(){
        controlGestioneImpiegati.clickIndietro("/com/example/GestioneRemoto/GestioneImpiegati/FXML/SchermataTimbraturaImpiegato");
    }


}

