package com.example.progettocompleto.Controller.ControlGestioneAutenticazione;



import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Mail.JavaMail;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.View.SchermateGestioneAutenticazione.SchermataLogin;
import com.example.progettocompleto.Start;
import com.example.progettocompleto.View.SchermateGestioneProfilo.SchermataVisualizzaCalendario;
import com.example.progettocompleto.View.Popup.PopupErrore;
import com.example.progettocompleto.View.Popup.PopupInformazione;
import com.example.progettocompleto.View.SchermateGestioneAutenticazione.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControlLogin{
    private Stage stage = Start.mainStage;
    private Stage stagePopup = new Stage();

    public ControlLogin(int matricola,String password){
        List<Object> datiProfilo = Daemon.getDatiProfilo(matricola);
        if(Daemon.verifyCredenziali(matricola,password)){
            if(datiProfilo.get(6).equals("Datore")){
                new EntityUtente((ArrayList<Object>) datiProfilo);
                SchermataPrincipaleDatore schermataPrincipaleDatore =  Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/SchermataPrincipaleDatore.fxml", stage, c -> new SchermataPrincipaleDatore(this, (ArrayList<Object>) datiProfilo));
                Thread thread = new Thread(schermataPrincipaleDatore);
                thread.start();
            }
            else if(datiProfilo.get(6).equals("Impiegato")){
                new EntityUtente((ArrayList<Object>) datiProfilo);
                SchermataPrincipaleImpiegato schermataPrincipaleImpiegato =  Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/SchermataPrincipaleImpiegato.fxml", stage, c -> new SchermataPrincipaleImpiegato(this, (ArrayList<Object>) datiProfilo));
                Thread thread = new Thread(schermataPrincipaleImpiegato);
                thread.start();
            }
            else{
                new EntityUtente((ArrayList<Object>) datiProfilo);
                SchermataPrincipaleAmministrativo schermataPrincipaleAmministrativo =  Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/SchermataPrincipaleAmministrativo.fxml", stage, c -> new SchermataPrincipaleAmministrativo(this, (ArrayList<Object>) datiProfilo));
                Thread thread = new Thread(schermataPrincipaleAmministrativo);
                thread.start();
            }
        }
        else{
            Stage stagePopup = new Stage();
            Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupErrore.fxml",stagePopup, c-> new PopupErrore("Dati inseriti non corretti",stagePopup));
            stagePopup.showAndWait();
            //SchermataLogin.show();
        }
    }
    public void clickRecuperaPassword(){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/SchermataRecuperoPassword.fxml",stage, c-> new SchermataRecuperoPassword(this));
    }
    //metodo che parte dalla Schermata Recupera Password
    public void clickIndietro(){
     Util.setScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/Login.fxml",stage, c->new SchermataLogin());

    }
    public ControlLogin(){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/SchermataRecuperoPassword.fxml",stage, c->new SchermataRecuperoPassword(this));
    }
    //metodo che parte dalla Schermata Recupera Password
    public void clickInvia(String mailText){

        if(Daemon.verifyMailPersonale(mailText)) {
            //setto tutte l'invio per la mail
            JavaMail javaMail = new JavaMail();
            //genero la password
            String password = generaPassword();
            Daemon.updatePassword(password,mailText);
            //metto l'inserimento della mail:
            javaMail.setTesto("Ecco la tua nuova password:" + password);
            javaMail.setOggetto("Recupero password");

            try {
                JavaMail.sendMail(mailText);
                Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupInformazione.fxml",stagePopup, c-> new PopupInformazione("Controlla la tua mail, la nuova password è stata inviata",stagePopup));
                stagePopup.showAndWait();
               SchermataLogin.show();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stagePopup, c->new PopupErrore("Mail Errata, riprova",stagePopup));
            stagePopup.showAndWait();
            SchermataRecuperoPassword.show();
        }

    }
    public void clickLogout(){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/Login.fxml", stage, c-> new SchermataLogin());
       Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupInformazione.fxml",stagePopup,c->new PopupInformazione("Popup Informazione",stagePopup));
        //Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupConferma.fxml",stagePopup,c->new PopupInformazione("Login effettuato con successo",stagePopup));
        stagePopup.showAndWait();
        SchermataLogin.show();
    }
    private String generaPassword(){
        String passwordGenerata = "";
        String caratteri ="abcdefghilmnopqrstuvzABCDEFGHILMNOPQRSTUVZ1234567890?@#[]";
        int dimensione = caratteri.length();
        for (int i = 0; i<10; i++){
            int randomIndexCharInAlphabet = (int)(Math.random()*dimensione);
            passwordGenerata += caratteri.charAt(randomIndexCharInAlphabet);
        }
        return passwordGenerata;
    }
    public void clickVisualizzaCalendario(){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneProfilo/VisualizzaCalendario.fxml",stage, c-> new SchermataVisualizzaCalendario());
    }

}
