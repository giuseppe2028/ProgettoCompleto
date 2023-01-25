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
   TextField matricola;



    @FXML
    private AnchorPane rettangolo;

    @FXML
    private AnchorPane sfondo;
    @FXML
    private PasswordField passwordField;
    private ControlLogin controlLogin;



    @FXML
    Hyperlink passwordDimenticata = new Hyperlink();


    @FXML
    public void clickRecuperaPassword(ActionEvent e) throws IOException {
        controlLogin.clickRecuperaPassword();
    }

    @FXML
    public void clickLogin(ActionEvent e) throws IOException {
        String password= passwordField.getText();
        int matricola1= Integer.parseInt(matricola.getText());
        ControlLogin controlLogin= new ControlLogin(matricola1, password);




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