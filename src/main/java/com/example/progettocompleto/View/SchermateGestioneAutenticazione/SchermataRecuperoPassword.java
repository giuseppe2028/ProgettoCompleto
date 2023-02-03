package com.example.progettocompleto.View.SchermateGestioneAutenticazione;


import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Controller.ControlGestioneAutenticazione.ControlLogin;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
        Util.setScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/SchermataRecuperoPassword.fxml",stage, c->new SchermataRecuperoPassword(new ControlLogin()));
    }

}
