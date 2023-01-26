package com.example.progettocompleto.Popup;

import com.example.progettocompleto.FileDiSistema.ControlInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;




/*public void initialize(){
        messaggioErrore.setText("Dati inseriti non corretti");

    }*/


public class PopupErrore {
    private Stage stage;
    private String message;




    @FXML
    Label intestazione;

    @FXML
    Label messaggioErrore;

    private ControlInterface controlInterface;
@FXML
public void initialize(){
    intestazione.setText("Errore");
    messaggioErrore.setText(this.message);
}



    public PopupErrore(String message, ControlInterface controlInterface,Stage stage) {
        this.stage = stage;
        this.controlInterface = controlInterface;
        //this.controlTimbratura = controlTimbratura;
        this.message = message;
    }



    @FXML
    public void clickOK(ActionEvent event)  {
        stage.close();
        controlInterface.clickOK();
    }




}
