package com.example.progettocompleto.GestioneAutenticazione.Control;



import com.example.progettocompleto.FileDiSistema.*;
import com.example.progettocompleto.GestioneAutenticazione.Schermate.SchermataPrincipaleAmministrativo;
import com.example.progettocompleto.GestioneAutenticazione.Schermate.SchermataPrincipaleDatore;
import com.example.progettocompleto.GestioneAutenticazione.Schermate.SchermataPrincipaleImpiegato;
import com.example.progettocompleto.GestioneAutenticazione.Schermate.SchermataRecuperoPassword;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlLogin {
    private Messaggio messaggio;
    private Stage stage = Start.mainStage;
    private Daemon db = new Daemon();
    private int matricola;
    private ErrorMex errorMex;
    private String mail, password;
    public void create(int matricola,String password){
        this.matricola = matricola;
        this.password = password;

    }
    //TODO il DBMS andra modificato secondo quanto specificato nei sequence
    public void clickLogin(ActionEvent e) throws IOException {


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
                SchermataPrincipaleImpiegato schermataPrincipaleImpiegato =  Util.setSpecificScene("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataPrincipaleImpiegato.fxml", stage, c -> new SchermataPrincipaleImpiegato(this, (ArrayList<Object>) datiProfilo));
                Thread thread = new Thread(schermataPrincipaleImpiegato);
                thread.start();


            }
            else{
                new EntityUtente((ArrayList<Object>) datiProfilo);

                SchermataPrincipaleAmministrativo schermataPrincipaleAmministrativo =  Util.setSpecificScene("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataPrincipaleAmministrativo.fxml", stage, c -> new SchermataPrincipaleAmministrativo(this, (ArrayList<Object>) datiProfilo));
                Thread thread = new Thread(schermataPrincipaleAmministrativo);
                thread.start();
            }
        }
        else{
            //todo modificare il popUp errore
            errorMex = new ErrorMex("Dati inseriti non corretti");
        }

    }
    public void clickRecuperaPassword(ActionEvent e){
        Util.setScene("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataRecuperoPassword.fxml",stage,c-> new SchermataRecuperoPassword(this));
    }
    //metodo che parte dalla Schermata Recupera Password
    public void clickIndietro(){
        //Util.setScene("/com/example/progettogaga/SchermataLogin.fxml",stage,c-> new SchermataLogin());

    }
    //metodo che parte dalla Schermata Recupera Password
    public void clickInvia(String mailText){
        //setto tutte l'invio per la mail
        JavaMail javaMail = new JavaMail();
        //genero la password
        String password = generaPassword();
        //metto l'inserimento della mail:
        javaMail.setTesto("Ecco la tua nuova password:"+password);
        javaMail.setOggetto("Recupero password");
        //invio la mail:
        messaggio = new Messaggio("La nuova password è stata inviata");
        try {
            JavaMail.sendMail(mailText);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        //creo il generatore di password:

    }
    public void clickLogout(ActionEvent e){
        //TODO implementare clickLogout
        //Util.setScene("/com/example/progetto2/Autenticazione/FXML/Login.fxml", stage, c-> );
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

}
