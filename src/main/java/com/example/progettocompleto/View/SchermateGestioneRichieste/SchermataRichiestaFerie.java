package com.example.progettocompleto.View.SchermateGestioneRichieste;

import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Controller.ControlGestioneRichieste.ControlGestioneRichieste;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SchermataRichiestaFerie {
    private static ControlGestioneRichieste controlGestioneRichieste;
    private static List<LocalDate> dataI;
    private static List<LocalDate> dataF;
    @FXML
    private AnchorPane rettangolo1;
    @FXML
    private DatePicker dataInizioDatePicker;
    @FXML
    private DatePicker dataFineDatePicker;

    public SchermataRichiestaFerie(ControlGestioneRichieste controlGestioneRichieste, List<LocalDate> dataI, List<LocalDate> dataF) {
        this.controlGestioneRichieste = controlGestioneRichieste;
        this.dataI = dataI;
        this.dataF = dataF;

    }

    @FXML
    public void initialize() {

        int y = 25;
        for (int i = 0; i < dataI.size(); ++i) {

            String data = String.valueOf(dataI.get(i));
            Label a = new Label();
            a.setText(data);
            a.setLayoutX(64);
            a.setLayoutY(y);
            y += 17;
            rettangolo1.getChildren().add(a);
        }
        int z = 25;
        for (int j = 0; j < dataF.size(); ++j) {

            String data = String.valueOf(dataF.get(j));
            Label a = new Label();
            a.setText(data);
            a.setLayoutX(260);
            a.setLayoutY(z);
            z += 17;

            rettangolo1.getChildren().add(a);
        }
        dataInizioDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate dateC, boolean empty) {
                super.updateItem(dateC, empty);
                for (int k = 0; k < dataI.size(); ++k) {
                    LocalDate start = dataI.get(k);
                    LocalDate end = dataF.get(k);
                    if ((dateC.isAfter(start) && dateC.isBefore(end)) || (dateC.getDayOfWeek() == DayOfWeek.SUNDAY) || dateC.isBefore(LocalDate.now())) {
                        setDisable(true);
                        return;
                    }
                }
            }
        });
        dataInizioDatePicker.setOnAction(e -> {
            LocalDate selectedDate = dataInizioDatePicker.getValue();
            dataFineDatePicker.setValue(selectedDate);
            dataFineDatePicker.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate dateC, boolean empty) {
                    super.updateItem(dateC, empty);
                    for (int k = 0; k < dataI.size(); ++k) {
                        LocalDate start = dataI.get(k);
                        LocalDate end = dataF.get(k);
                        if ((dateC.isAfter(start) && dateC.isBefore(end)) || (dateC.getDayOfWeek() == DayOfWeek.SUNDAY) || dateC.isBefore(LocalDate.now()) || dateC.isBefore(selectedDate)) {
                            setDisable(true);
                            return;
                        }
                    }
                }
            });
        });
    }

    public void clickIndietro(ActionEvent e) {
        Util.ritorno("/com/example/progettocompleto/FXML/GestioneRichieste/SchermataGestioneRichieste.fxml");
    }


    public void clickInvia(ActionEvent e) throws SQLException {
        LocalDate dataInizio = dataInizioDatePicker.getValue();
        ;
        LocalDate dataFine = dataFineDatePicker.getValue();

        controlGestioneRichieste.clickInviaFerie(dataInizio, dataFine);
    }
    public static void show(){
            Util.setScene("/com/example/progettocompleto/FXML/GestioneRichieste/SchermataRichiestaFerie.fxml", Start.mainStage, c->new SchermataRichiestaFerie(controlGestioneRichieste,dataI,dataF));

    }
}

