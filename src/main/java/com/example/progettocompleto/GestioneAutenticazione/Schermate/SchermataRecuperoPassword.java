package com.example.progettocompleto.GestioneAutenticazione.Schermate;


import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneAutenticazione.Control.ControlLogin;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SchermataRecuperoPassword {
    private static Stage stage = Start.mainStage;

    private  ControlLogin controlLogin;
    @FXML
    private TextField mailText;
    public SchermataRecuperoPassword(ControlLogin controlLogin){
        this.controlLogin = controlLogin;
    }
    @FXML
    public void clickIndietro(ActionEvent e){
    controlLogin.clickIndietro();
    }
    @FXML
    public void clickInvia(ActionEvent e) throws Exception {

       controlLogin.clickInvia(mailText.getText());

    }
    public static void show(){
        Util.setScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/SchermataRecuperoPassword.fxml",stage,c->new SchermataRecuperoPassword(new ControlLogin()));
    }

}
