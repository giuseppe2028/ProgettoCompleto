package com.example.progettocompleto.GestioneAutenticazione.Schermate;

import com.example.progettocompleto.Start;
import com.example.progettocompleto.GestioneAutenticazione.Control.ControlLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class SchermataLogin {
    private Stage stage = Start.mainStage;
    @FXML
   TextField matricola;
    @FXML
    private PasswordField passwordField;
    private ControlLogin controlLogin;

    public void clickRecuperaPassword(ActionEvent e) throws IOException {
        controlLogin.clickRecuperaPassword();
    }


    public void clickLogin(ActionEvent e) throws IOException {
        String password= passwordField.getText();
        int matricola1= Integer.parseInt(matricola.getText());
        ControlLogin controlLogin= new ControlLogin(matricola1, password);
    }

    @FXML
    public void show(){
        //TODO implementare show
    }
}