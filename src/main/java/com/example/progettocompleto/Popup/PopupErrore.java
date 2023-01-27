package com.example.progettocompleto.Popup;

import com.example.progettocompleto.FileDiSistema.ControlInterfaceErrore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;




/*public void initialize(){
        messaggioErrore.setText("Dati inseriti non corretti");

    }*/


public class PopupErrore {
    @FXML
    Button bottoneOK;
    private Stage stage;
    private String message;




    @FXML
    Label intestazione;

    @FXML
    Label messaggioErrore;

@FXML
public void initialize(){
    intestazione.setText("Errore");
    messaggioErrore.setText(this.message);
}



    public PopupErrore(String message,Stage stage) {
        this.stage = stage;
        //this.controlTimbratura = controlTimbratura;
        this.message = message;

    }
    @FXML
    public void clickOK(ActionEvent event)  {
        stage.close();
    }




}
