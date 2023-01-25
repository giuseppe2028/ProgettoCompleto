package com.example.progettocompleto.GestioneProfilo.Schermate;


import com.example.progettocompleto.GestioneProfilo.Control.ControlTimbraturaRemoto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    @FXML
    TextField motivazione;
    private String[] ora = {"08","09" , "10","11","12","13","14", "15","16"};
    private String[] minuto = {"00", "05", "10", "15", "20", "25", "30"};
    ControlTimbraturaRemoto controlTimbraturaRemoto;

    public SchermataTimbraturaRemoto(ControlTimbraturaRemoto controlTimbraturaRemoto){
       this.controlTimbraturaRemoto=controlTimbraturaRemoto;

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

    public void clickConferma(ActionEvent e) throws SQLException {
        LocalDate data = datePicker.getValue();
        LocalTime orario = LocalTime.of(Integer.parseInt(ore.getValue()), Integer.parseInt(minuti.getValue()));
        String motivazione1= motivazione.getText();
        controlTimbraturaRemoto.clickConferma(data, orario, motivazione1);
    }


}
