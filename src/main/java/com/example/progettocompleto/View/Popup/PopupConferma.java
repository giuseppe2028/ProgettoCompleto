package com.example.progettocompleto.View.Popup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupConferma {
    Stage stage;

    private String message;

    @FXML
    Label messaggioConferma;

    @FXML
    Text intestazione;


    @FXML
    public void initialize(){
        intestazione.setText("Conferma");
        messaggioConferma.setText(this.message);
    }





    public PopupConferma(String message, Stage stage){
        stage.setResizable(false);
        this.stage = stage;
        this.message = message;

    }
    @FXML
    public void clickConferma(){
        stage.close();
    }


}

