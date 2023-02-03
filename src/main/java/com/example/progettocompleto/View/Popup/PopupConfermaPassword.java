package com.example.progettocompleto.View.Popup;



import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlGestioneImpiegati;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupConfermaPassword {

    Stage stage = Start.mainStage;

    private String password;

    private int matricola;
    @FXML
    private PasswordField confermaPassword;



    @FXML
    private Label intestazione;

    private ControlGestioneImpiegati controlGestioneImpiegati = new ControlGestioneImpiegati();


    @FXML
    public void initialize(){
        intestazione.setText("Errore");
        password = confermaPassword.getText();
    }


    public PopupConfermaPassword( Stage stage, int matricola) {
    this.stage = stage;
    this.matricola = matricola;

    }


//rivedi bene
    @FXML
    public void clickConferma(ActionEvent e)  {
        stage.close();
        String password = confermaPassword.getText();
        controlGestioneImpiegati.clickConfermaPassword(password, matricola);
    }
}
