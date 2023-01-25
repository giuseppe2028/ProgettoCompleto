package com.example.progettocompleto.PopUp;

import com.example.progetto2.Control.ControlTimbratura;
import com.example.progetto2.Start;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupInformazione {
    Stage stage = Start.mainStage;

    private String message;

    @FXML
    Label messaggioInformazione;

    @FXML
     Label intestazione;

    ControlTimbratura controlTimbratura;

    @FXML
    public void initialize(){
        intestazione.setText("Informazione");
        messaggioInformazione.setText(this.message);
    }







    public PopupInformazione(String message, ControlTimbratura controlTimbratura){
        this.controlTimbratura=new ControlTimbratura();
        this.message = message;
        //messaggioInformazione.setText(this.message);

    }

    @FXML
    public void clickOK(ActionEvent event) throws IOException {
        stage.close();
        controlTimbratura.clickOK();
    }
}

