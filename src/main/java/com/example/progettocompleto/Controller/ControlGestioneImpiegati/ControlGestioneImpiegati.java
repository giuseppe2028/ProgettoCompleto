package com.example.progettocompleto.Controller.ControlGestioneImpiegati;

import com.example.progettocompleto.Contenitori.Impiegati;
import com.example.progettocompleto.Contenitori.Stipendio;
import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Model.Util;

import com.example.progettocompleto.Start;
import com.example.progettocompleto.View.Popup.PopupConfermaPassword;
import com.example.progettocompleto.View.Popup.PopupErrore;
import com.example.progettocompleto.View.Popup.PopupInformazione;
import com.example.progettocompleto.View.SchermateGestioneImpiegati.*;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ControlGestioneImpiegati{
    private int matricolaImpiegato;
    private Stage stagePopup = new Stage();
    private Stage stage = Start.mainStage;
    public ControlGestioneImpiegati() {
        Util.setScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataGestioneImpiegati.fxml",stage, c->new SchermataGestioneImpiegati(this,Daemon.getImpiegati()));
    }

    public void clickVisualizzaDati(int matricola) {
        List<Object> imp=  Daemon.getDatiProfiloImpiegato(matricola);
        InputStream im= Daemon.getFotoProfilo(matricola);
        Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataVisualizzaImpiegato.fxml", stage, c->new SchermataVisualizzaImpiegato(this, imp, im) );
    }

    public void clickVisualizzaStipendio(int matricola,String mese, int anno) {
        Stipendio stipendio = Daemon.getStipendio(matricola, mese,anno);
        Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataVisualizzaStipendio.fxml", stage, c-> new SchermataVisualizzaStipendioImpiegato(this,stipendio));
    }



    public void clickRegistra() {
        Util.setScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataRegistrazioneImpiegato.fxml", stage, c-> new SchermataRegistrazioneImpiegato(this));

    }

   public void clickDisattiva() {
        Util.setScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataDisattivaImpiegato.fxml", stage, c-> new SchermataDisattivaImpiegato(this));
    }

    public void clickTimbraturaImpiegato(int matricola) {

       SchermataTimbraturaImpiegato schermataTimbraturaImpiegato= Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataTimbraturaImpiegato.fxml", stage, c-> new SchermataTimbraturaImpiegato(this, matricola));
        Thread thread = new Thread(schermataTimbraturaImpiegato);
        thread.start();
    }
    public void clickIndietro(String fxml){
        Util.ritorno(fxml);
    }

    public void clickConferma(LocalDate data, LocalTime orario, int matricola) throws SQLException {
        if (Daemon.controlloTurno(data, matricola)) {
            if (Daemon.verifyTimbratura(data, orario, matricola)) {
                //todo popup errore
            } else {
                //todo da fare
                String tipoTurno= Daemon.getTurno(data, matricola);
               // LocalDate dataTurno= Daemon.getDataTurno(data, matricola);
              //  Daemon.insertTimbratura(data, orario, matricola, tipoTurno, dataTurno);
                Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
                stagePopup.showAndWait();
                //SchermataTimbraturaImpiegato.show();

            }
        }else {
            //todo popup errore
        }
    }


    public void clickRegistra(String nome, String cognome,long recapito, String mailPersonale,String indirizzo,String iban,int servizio, String ruolo,char sesso, boolean reperibile,LocalDate dataNascita,String coficeFiscale){
        System.out.println(nome+cognome+mailPersonale+iban+indirizzo+servizio+ruolo+sesso+reperibile+dataNascita.toString());
        //prendo la matricola massima
        Stage stage1=new Stage();
        int matricola = Daemon.getMaxMatricola()+1;
        String mail = nome+"."+cognome+"@azienda.it";
        String password = nome+cognome+"123";
        Daemon.updateImpiegato(matricola,nome,cognome,sesso,coficeFiscale,dataNascita,indirizzo,recapito,mailPersonale,iban,mail,password,ruolo,reperibile,servizio,LocalDate.now(),null,26,30,6,false,null);
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stage1,c-> new PopupErrore("Succhiami la ciolla",stage1));
        stage1.show();
        //todo mettere che viene inserita la mail
    }
    List<Impiegati> listaFiltrata;
    public List<Impiegati> filtra(String ruolo, int servizio,List<Impiegati> lista){
        List<Impiegati> listaDaritornare;
        if(servizio == 0 && ruolo == null){
            return Daemon.getImpiegati();
        }
        if(servizio != 0){
            listaFiltrata = lista.stream().filter(s-> s.getServizio() == servizio).toList();
            return listaFiltrata;
        }
        else{
            listaDaritornare = lista;
        }
        if(ruolo != null){
            listaFiltrata = lista.stream().filter(s-> s.getRuolo().startsWith(ruolo)).collect(Collectors.toList());
            return listaFiltrata;
        }else{
            listaDaritornare = lista;
        }

        return listaDaritornare;
    }

    public void clickCalendario() {
        //todo da rivedere
        Util.setScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataCalendarioImpiegati.fxml", stage, c-> new SchermataVisualizzaCalendarioImpiegati(Daemon.getTurni()));
    }
    public Stipendio getResocontoStipendio(int matricola, String mese, int anno){
        return Daemon.getStipendio(matricola,mese,anno);
    }
    public void clickDisattiva(int matricolaImpiegato){
        this.matricolaImpiegato = matricolaImpiegato;
        Util.setSpecificScene("",stagePopup, c-> new PopupConfermaPassword(stagePopup, EntityUtente.getMatricola()));
        stagePopup.showAndWait();

    }
    public void clickConfermaPassword(  String password,  int matricola) {
        if (Daemon.verifyPasswordA(password, matricola)) {
            Daemon.updateImpiegatoDisattivato(matricola);
            Util.setScenePopup("/com/example/progettocompleto/PopUp/FXML/PopUpInformazione.fxml", stagePopup, c -> new PopupInformazione("Azione eseguita con successo", stagePopup));
            stagePopup.showAndWait();
            SchermataGestioneImpiegati.show(this, Daemon.getImpiegati());
        } else {
            Util.setScenePopup("/com/example/progettocompleto/PopUp/FXML/PopUpErrore.fxml", stagePopup, c -> new PopupErrore("Password errata", stagePopup));
            stagePopup.showAndWait();
            SchermataDisattivaImpiegato.show();
        }

    }
}