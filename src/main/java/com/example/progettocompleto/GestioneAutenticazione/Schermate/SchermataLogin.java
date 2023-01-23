package com.example.progettocompleto.GestioneAutenticazione.Schermate;


import com.example.progettocompleto.Start;
import com.example.progettocompleto.GestioneAutenticazione.Control.ControlLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SchermataLogin {
    private Stage stage = Start.mainStage;
    //test
    @FXML
    private Button bottoneLogin;

    @FXML
    private TextField matricola;

    @FXML
    private TextField mail;


    @FXML
    private AnchorPane rettangolo;

    @FXML
    private AnchorPane sfondo;
    @FXML
    private PasswordField passwordField;
    private ControlLogin controlLogin;

    public SchermataLogin() {
        controlLogin = new ControlLogin();
    }

    @FXML
    Hyperlink passwordDimenticata = new Hyperlink();


    @FXML
    public void clickRecuperaPass(ActionEvent e) throws IOException {
        controlLogin.clickRecuperaPassword(e);
    }
    //DIRE NELL'ODD CHE NON METTIAMO IL METODO INSERSCI CREDENZIALI PER NON PREMERE IL TASTO INVIO
    @FXML
    public void clickLogin(ActionEvent e) throws IOException {
        controlLogin.create(Integer.parseInt(matricola.getText()), passwordField.getText());
        controlLogin.clickLogin(e);
    }
    @FXML
    public void clickCalendario(ActionEvent e){
        // Util.setScene("/com/example/progettogaga/Calendario/Calendario.fxml", stage, c -> new Calendario());
    }
    @FXML
    public void show(){
        //TODO implementare show
    }
}