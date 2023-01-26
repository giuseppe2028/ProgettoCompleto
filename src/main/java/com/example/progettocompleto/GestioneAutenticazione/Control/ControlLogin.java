package com.example.progettocompleto.GestioneAutenticazione.Control;



import com.example.progettocompleto.FileDiSistema.*;
import com.example.progettocompleto.GestioneAutenticazione.Schermate.*;
import com.example.progettocompleto.Popup.PopupErrore;
import com.example.progettocompleto.Popup.PopupInformazione;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControlLogin implements ControlInterfaceInformazione,ControlInterfaceErrore{
    private Stage stage = Start.mainStage;
    private Stage stagePopup = new Stage();

    public ControlLogin(int matricola,String password){
        List<Object> datiProfilo = Daemon.getDatiProfilo(matricola);
        if(Daemon.verifyCredenziali(matricola,password)){
            if(datiProfilo.get(6).equals("Datore")){
                new EntityUtente((ArrayList<Object>) datiProfilo);
                SchermataPrincipaleDatore schermataPrincipaleDatore =  Util.setSpecificScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/SchermataPrincipaleDatore.fxml", stage, c -> new SchermataPrincipaleDatore(this, (ArrayList<Object>) datiProfilo));
                Thread thread = new Thread(schermataPrincipaleDatore);
                thread.start();
            }
            else if(datiProfilo.get(6).equals("IMPIEGATO")){
                new EntityUtente((ArrayList<Object>) datiProfilo);
                SchermataPrincipaleImpiegato schermataPrincipaleImpiegato =  Util.setSpecificScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/SchermataPrincipaleImpiegato.fxml", stage, c -> new SchermataPrincipaleImpiegato(this, (ArrayList<Object>) datiProfilo));
                Thread thread = new Thread(schermataPrincipaleImpiegato);
                thread.start();
            }
            else{
                new EntityUtente((ArrayList<Object>) datiProfilo);
                SchermataPrincipaleAmministrativo schermataPrincipaleAmministrativo =  Util.setSpecificScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/SchermataPrincipaleAmministrativo.fxml", stage, c -> new SchermataPrincipaleAmministrativo(this, (ArrayList<Object>) datiProfilo));
                Thread thread = new Thread(schermataPrincipaleAmministrativo);
                thread.start();
            }
        }
        else{
            Stage stagePopup = new Stage();
            Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stagePopup, c-> new PopupErrore("Dati inseriti non corretti",this,stagePopup));
            stagePopup.showAndWait();
            SchermataLogin.show();
        }
    }
    public void clickRecuperaPassword(){
        Util.setScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/SchermataRecuperoPassword.fxml",stage,c-> new SchermataRecuperoPassword(this));
    }
    //metodo che parte dalla Schermata Recupera Password
    public void clickIndietro(){
     Util.setScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/Login.fxml",stage,c->new SchermataLogin());

    }
    public ControlLogin(){
        Util.setScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/SchermataRecuperoPassword.fxml",stage,c->new SchermataRecuperoPassword(this));
    }
    //metodo che parte dalla Schermata Recupera Password
    public void clickInvia(String mailText){

        if(Daemon.verifyMailPersonale(mailText)) {
            //setto tutte l'invio per la mail
            JavaMail javaMail = new JavaMail();
            //genero la password
            String password = generaPassword();
            //metto l'inserimento della mail:
            javaMail.setTesto("Ecco la tua nuova password:" + password);
            javaMail.setOggetto("Recupero password");

            try {
                JavaMail.sendMail(mailText);
                Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup, c-> new PopupInformazione("Controlla la tua mail, la nuova password è stata inviata",this,stagePopup));
                //todo mettere il recupera password dbms aggiornare
                stagePopup.show();

                //Daemon.updatePassword(password,matricola);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stagePopup, c->new PopupErrore("Mail Errata, riprova",this,stagePopup));
            stagePopup.show();
        }

    }
    public void clickLogout(ActionEvent e){
        Util.setScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/Login.fxml", stage, c-> new SchermataLogin());
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c->new PopupInformazione("Logout effettuato con successo",this,stagePopup));
        stagePopup.show();
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
    @Override
    public void clickOKInformazione(){

    }
    @Override
    public void clickOKErrore(){
        SchermataRecuperoPassword schermataRecuperoPassword;
    }

}
