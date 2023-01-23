package com.example.progettocompleto.GestioneRichieste.Schermate;

import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Control.ControlGestioneRichieste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SchermataRichiestaFerie {
    private ControlGestioneRichieste controlGestioneRichieste;
    List<LocalDate> dataI;
    List<LocalDate> dataF;
    @FXML
    AnchorPane rettangolo1;
    @FXML
    DatePicker dataInizioDatePicker;
    @FXML
    DatePicker dataFineDatePicker;

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
                            for(int k = 0; k < dataI.size(); ++k){
                                LocalDate start = dataI.get(k);
                                LocalDate end = dataF.get(k);
                                if ((dateC.isAfter(start) && dateC.isBefore(end))||(dateC.getDayOfWeek() == DayOfWeek.SUNDAY)||dateC.isBefore(LocalDate.now())) {
                                    setDisable(true);
                                    return;
                                }
                            }
                        }
                });
        dataInizioDatePicker.setOnAction(e->{
            LocalDate selectedDate = dataInizioDatePicker.getValue();
            dataFineDatePicker.setValue(selectedDate);
            dataFineDatePicker.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate dateC, boolean empty) {
                    super.updateItem(dateC, empty);
                    for(int k = 0; k < dataI.size(); ++k){
                        LocalDate start = dataI.get(k);
                        LocalDate end = dataF.get(k);
                        if ((dateC.isAfter(start) && dateC.isBefore(end))||(dateC.getDayOfWeek() == DayOfWeek.SUNDAY)||dateC.isBefore(LocalDate.now())||dateC.isBefore(selectedDate)) {
                            setDisable(true);
                            return;
                        }
                    }
                }
            });
        });
            }
            public void clickIndietro(ActionEvent e)
            {
                Util.ritorno("/com/example/GestioneRemoto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml");
            }
}

     /*
    public void clickInvia(ActionEvent e){
        LocalDate dataInizio = dataInizioDatePicker.getValue();;
        LocalDate dataFine = dataFineDatePicker.getValue();

    controlGestioneRichieste.clickInviaFerie(dataInizio, dataFine);
    }
    public void clickIndietro(){
       // controlGestioneRichieste.clickIndietro();

    }
*/



