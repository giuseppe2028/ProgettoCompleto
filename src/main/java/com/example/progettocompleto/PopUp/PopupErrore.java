package com.example.progettocompleto.PopUp;

import com.example.progetto2.Control.ControlTimbratura;
import com.example.progetto2.Start;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;




/*public void initialize(){
        messaggioErrore.setText("Dati inseriti non corretti");

    }*/


public class PopupErrore {
    Stage stage = Start.mainStage;
    private String message;




    @FXML
    Label intestazione;

    @FXML
    Label messaggioErrore;

    ControlTimbratura controlTimbratura;
@FXML
public void initialize(){
    intestazione.setText("Errore");
    messaggioErrore.setText(this.message);
}



    public PopupErrore(String message, ControlTimbratura controlTimbratura) {
        this. controlTimbratura=new ControlTimbratura();
        //this.controlTimbratura = controlTimbratura;
        this.message = message;
    }



    @FXML
    public void clickOK(ActionEvent event)  {
        stage.close();
        controlTimbratura.clickOK();

    }




}
