package com.example.progettocompleto.GestioneProfilo.Control;



import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneProfilo.Schermate.SchermataModificaPassword;
import com.example.progettocompleto.GestioneProfilo.Schermate.SchermataModificaProfilo;
import com.example.progettocompleto.GestioneProfilo.Schermate.SchermataVisualizzaProfilo;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ControlVisualizzaProfilo {
    private Stage stage = Start.mainStage;
    Parent root;
    private int matricola;
    private boolean esito;
    private String vecchia;
    private String nuova;
    private String conferma;


    List<Object> datiProfilo;
    List<String> datiModificati;

    /*  public controlVisualizzaProfilo(){

      }*/
    public void clickVisualizzaProfilo() {
        datiProfilo = EntityUtente.getDatiProfilo();
        SchermataVisualizzaProfilo schermataVisualizzaProfilo1= Util.setSpecificScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataVisualizzaProfilo.fxml", stage, c->new SchermataVisualizzaProfilo(this, (ArrayList<Object>) datiProfilo));
    }

    public void clickModifica(){
        Util.setScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataModificaProfilo.fxml", stage, c-> new SchermataModificaProfilo(this));
    }
    public void clickModificaPassword(){
        Util.setScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataModificaPassword.fxml", stage, c-> new SchermataModificaPassword(this));
    }
    public boolean verifyPassword(String nuovapass, String confpass) {
        boolean isValid = nuovapass.length() <= 30;
        return isValid && nuovapass.equals(confpass);
    }

    public void clickConferma(ActionEvent e, String vecpass, String nuovapass, String confpass) throws IOException {

        if (verifyPassword(nuovapass, confpass)) {
            boolean modificata = Daemon.modificaPassword(vecpass, nuovapass);
            if (modificata) {

                Alert a= new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Password cambiata con successo!");
                a.showAndWait();
            } else {
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setTitle("Errore");
                a.setContentText("la vecchia password non corrisponde");
                a.showAndWait();
            }
        } else {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setTitle("Errore");
            a.setContentText("le password non sono valide");
            a.showAndWait();
        }

    }
    public void compila(String vecchia, String nuova, String conferma){
        this.vecchia=vecchia;
        this.nuova=nuova;
        this.conferma=conferma;
    }
    public void compila(List datiModificati){

        this.datiModificati= datiModificati;

    }

    public void clickOK(){
//TODO implementare

    }
    /* public void clickIndietro(){
        List<Object> dati = EntityUtente.getDatiProfilo();
         ControlLogin c1= new ControlLogin();
     Util.setSpecificScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/SchermataPrincipaleDatore.fxml", stage, c->new SchermataPrincipaleDatore(c1,dati ));
     }*/
    public boolean verifyInsert(String recapito, String iban){
        boolean isValid= recapito.length()<=12;
        boolean isVali= iban.length()>26 ;
        return isValid && isVali;
    }
    public void clickSalva(List<Object> datiModificati, InputStream path) throws IOException {
        String recapito= datiModificati.get(0).toString();
        String iban= datiModificati.get(1).toString();
        String indi= datiModificati.get(2).toString();
        String mail= datiModificati.get(3).toString();

        if(verifyInsert(recapito, iban)){
            boolean modificata= Daemon.modificaDati(Double.valueOf(recapito), iban, indi, mail, path);

            if(modificata){
                Alert a= new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Dati cambiati con successo!");
                a.showAndWait();
                ArrayList<Object> datiProfilo;
                datiProfilo=(ArrayList<Object>) EntityUtente.getDatiProfilo();
                Util.setSpecificScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/SchermataVisualizzaProfilo.fxml", stage, c-> new SchermataVisualizzaProfilo(this,datiProfilo ));
            }else {
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setTitle("Errore");
                a.setContentText("Impossibile cambiare i dati");
                a.showAndWait();
            }
        }else{
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setTitle("Errore");
            a.setContentText("l'iban o il recapito telefonico sono sbagliati");
            a.showAndWait();
        }

    }

    public void clickIndietro(String fxml){
        Util.ritorno(fxml);
    }


}


