package com.example.progettocompleto.GestioneProfilo.Schermate;




import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import org.w3c.dom.Entity;

import java.io.IOException;
import java.sql.SQLException;


public class SchermataModificaPassword {
    @FXML
    Button confermaButton;
    private final ControlVisualizzaProfilo controlVisualizzaProfilo;

    public SchermataModificaPassword(ControlVisualizzaProfilo controlVisualizzaProfilo) {
        this.controlVisualizzaProfilo = controlVisualizzaProfilo;
    }

    @FXML
    private PasswordField vecchiaPassword;
    @FXML
    private PasswordField nuovaPassword;
    @FXML
    private PasswordField confermaNuovaPassword;
    public void verificaPassword() {
        nuovaPassword.setOnKeyPressed(event -> {
            String nuovapassword = nuovaPassword.getText();
            String conferma = confermaNuovaPassword.getText();
            // Conta il numero di caratteri presenti nella password
            int count = nuovapassword.length();
            int countConferma = conferma.length();

            // Se il count è maggiore di 7, abilita il tasto di confermaPassword
            if (count > 7 && countConferma > 7 ) {
                confermaButton.setDisable(false);
            } else {
                confermaButton.setDisable(true);
            }
        });
    }


    public void clickconfermaPassword(ActionEvent e) throws IOException, SQLException {
int matricola= EntityUtente.getMatricola();
        String vecpass= vecchiaPassword.getText();
        String nuovapass= nuovaPassword.getText();
        String confpass= confermaNuovaPassword.getText();
        controlVisualizzaProfilo.clickConferma(vecpass,nuovapass,confpass, matricola);

    }
    public void clickIndietro(ActionEvent e){
        controlVisualizzaProfilo.clickIndietro("com/example/GestioneRemoto/GestioneProfilo/FXML/SchermataVisualizzaProfilo.fxml");
    }




}