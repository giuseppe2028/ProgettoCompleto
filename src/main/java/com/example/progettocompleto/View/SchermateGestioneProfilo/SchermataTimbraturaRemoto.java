package com.example.progettocompleto.View.SchermateGestioneProfilo;


import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlTimbraturaRemoto;
import com.example.progettocompleto.Timer.Timer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimerTask;

public class SchermataTimbraturaRemoto implements Runnable{
    @FXML
    private Label dataCorrente;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<String> ore;
    @FXML
    private ChoiceBox<String> minuti;
    @FXML
    private TextField motivazione;
    private java.util.Timer timer = new java.util.Timer();
    private String[] ora = {"08","09" , "10","11","12","13","14", "15","16"};
    private String[] minuto = {"00", "05", "10", "15", "20", "25", "30"};
    ControlTimbraturaRemoto controlTimbraturaRemoto;

    public SchermataTimbraturaRemoto(ControlTimbraturaRemoto controlTimbraturaRemoto){
       this.controlTimbraturaRemoto=controlTimbraturaRemoto;

    }
    @FXML
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
    public void clickConferma(ActionEvent e) throws SQLException {
        LocalDate data = datePicker.getValue();
        LocalTime orario = LocalTime.of(Integer.parseInt(ore.getValue()), Integer.parseInt(minuti.getValue()));
        String motivazione1= motivazione.getText();
        controlTimbraturaRemoto.clickConferma(data, orario, motivazione1);
    }

//todo show
}
