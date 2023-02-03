package com.example.progettocompleto.View.SchermateGestioneImpiegati;


import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlGestioneImpiegati;
import com.example.progettocompleto.Start;
import com.example.progettocompleto.Timer.Timer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimerTask;

public class SchermataTimbraturaImpiegato implements Runnable {
    @FXML
    private Label dataCorrente;
    @FXML
    private DatePicker datePicker;
    private java.util.Timer timer = new java.util.Timer();
    @FXML
    private ChoiceBox<String> ore;
    @FXML
    private ChoiceBox<String> minuti;
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
    @Override
    public void run() {
        int x = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String oraCorrente;
                int secondo = Timer.getSecond();
                int minuto = Timer.getMinute();
                int ora = Timer.getHour();
                int giorno = Timer.getDay();
                int mese = Timer.getMonth();
                int anno = Timer.getYear();
                String input = giorno+"-"+mese+"-"+anno+"T"+ora + ":" + minuto +":" + secondo;
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
                try {
                    Date date = inputFormat.parse(input);
                    DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    oraCorrente = outputFormat.format(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(()->{
                    dataCorrente.setText(oraCorrente);
                });
            }
        },0,1000);

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
    @FXML
    public void clickIndietro(ActionEvent e){
        controlGestioneImpiegati.clickIndietro("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataGestioneImpiegati.fxml");
    }
public void show(){
   Util.setScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataTimbraturaImpiegato.fxml", Start.mainStage, c-> new SchermataTimbraturaImpiegato(new ControlGestioneImpiegati(),matricola));
}

}

