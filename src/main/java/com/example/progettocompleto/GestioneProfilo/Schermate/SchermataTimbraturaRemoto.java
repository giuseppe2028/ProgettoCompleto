package com.example.progettocompleto.GestioneProfilo.Schermate;


import com.example.progettocompleto.GestioneProfilo.Control.ControlTimbraturaRemoto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class SchermataTimbraturaRemoto {
    @FXML
    DatePicker datePicker;
    @FXML
    ChoiceBox<String> ore;
    @FXML
    ChoiceBox<String> minuti;
    private String[] ora = {"08","09" , "10","11","12","13","14", "15","16"};
    private String[] minuto = {"00", "05", "10", "15", "20", "25", "30"};
    ControlTimbraturaRemoto controlTimbraturaRemoto;
    int matricola;
    public SchermataTimbraturaRemoto(ControlTimbraturaRemoto controlTimbraturaRemoto, int matricola){
       this.controlTimbraturaRemoto=controlTimbraturaRemoto;
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
    //TODO unico compila
    public LocalDate compila(){
        LocalDate dataArrivo= datePicker.getValue();
        return dataArrivo;
    }
    public int compila2(){
        //   LocalTime[] orario = new LocalTime[2];
        LocalTime orario = LocalTime.parse(ore.getValue());
        //orario[1] = LocalTime.parse(minuti.getValue());
        return Integer.parseInt(ore.getValue());
    }
    public int compila3(){
        //   LocalTime[] orario = new LocalTime[2];
        //orario[1] = LocalTime.parse(minuti.getValue());
        return Integer.parseInt(minuti.getValue());
    }
    public void clickConferma(ActionEvent e) throws SQLException {
        LocalDate data = compila();
        LocalTime orario = LocalTime.of(compila2(), compila3());
        controlTimbraturaRemoto.clickConferma(data, orario, matricola);
    }


}
