package com.example.progettocompleto.View.SchermateGestioneAutenticazione;

import com.example.progettocompleto.Controller.ControlGestioneAutenticazione.ControlLogin;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SchermataLogin {
    @FXML
    private TextField matricola;
    @FXML
    private PasswordField passwordField;
    private static Stage stage = Start.mainStage;

    private ControlLogin controlLogin;

    public SchermataLogin() {
            controlLogin = new ControlLogin();
    }

    @FXML
    public void clickRecuperaPassword(ActionEvent e) throws IOException {
        controlLogin.clickRecuperaPassword();
    }
    @FXML
    public void clickLogin(ActionEvent e) throws IOException{
        controlLogin = new ControlLogin(Integer.parseInt(matricola.getText()),passwordField.getText());
    }


   public static void show(){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/Login.fxml",stage, c->new SchermataLogin());
    }
}