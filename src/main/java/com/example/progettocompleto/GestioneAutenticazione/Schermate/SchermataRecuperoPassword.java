package com.example.progettocompleto.GestioneAutenticazione.Schermate;


import com.example.progettocompleto.GestioneAutenticazione.Control.ControlLogin;
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

    private ControlLogin controlLogin;

    public void setControlLogin(ControlLogin controlLogin){
        this.controlLogin = controlLogin;
    }
    private Label label = new Label();
    private Stage stage;
    public SchermataRecuperoPassword(ControlLogin controlLogin){
    this.controlLogin = controlLogin;
    }
    private Scene scene;
    private Parent root;
    @FXML
    private TextField mailText = new TextField();
    @FXML
    private Pane rettangolo = new Pane();
    @FXML
    Button bottoneInvia = new Button();
    @FXML
    Button bottoneIndietro = new Button();

    @FXML
    public void clickIndietro(ActionEvent e){
    controlLogin.clickIndietro();
    }
    @FXML
    public void clickInvia(ActionEvent e) throws Exception {

       controlLogin.clickInvia(mailText.getText());

    }

}
