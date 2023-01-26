package com.example.progettocompleto.GestioneAutenticazione.Schermate;

import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.Start;
import com.example.progettocompleto.GestioneAutenticazione.Control.ControlLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class SchermataLogin {
    private static Stage stage = Start.mainStage;
    @FXML
   TextField matricola;
    @FXML
    private PasswordField passwordField;
    private ControlLogin controlLogin;

    public SchermataLogin() {
        try {
            controlLogin = new ControlLogin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickRecuperaPassword(ActionEvent e) throws IOException {
        controlLogin.clickRecuperaPassword();
    }


    public void clickLogin(ActionEvent e) throws IOException {
        controlLogin.clickLogin(Integer.parseInt(matricola.getText()),passwordField.getText());
    }

    @FXML
    public static void show(){
        Util.setScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/Login.fxml",stage,c->new SchermataLogin());
    }
}