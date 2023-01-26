package com.example.progettocompleto.GestioneImpiegati.Control;

import com.example.progettocompleto.Contenitori.Impiegati;
import com.example.progettocompleto.FileDiSistema.ControlInterface;
import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneImpiegati.Schermate.*;
import com.example.progettocompleto.Popup.PopupErrore;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ControlGestioneImpiegati implements ControlInterface {
    Stage stage = Start.mainStage;
    public ControlGestioneImpiegati() {
        Util.setScene("/com/example/progettocompleto/GestioneImpiegati/FXML/SchermataGestioneImpiegati.fxml",stage, c->new SchermataGestioneImpiegati(this));
    }

    public void clickVisualizzaDati(int matricola) {
        List<Object> imp=  Daemon.getDatiProfilo(matricola);
        Util.setSpecificScene("/com/example/progettocompleto/GestioneImpiegati/FXML/SchermataVisualizzaImpiegato.fxml", stage, c->new SchermataVisualizzaImpiegato(this, imp) );
    }

    public void clickVisualizzaStipendio() {
        //TODO query al db, fattelo tu
        Util.setSpecificScene("/com/example/progettocompleto/GestioneImpiegato/FXML/SchermataVisualizzaStipendio.fxml", stage, c-> new SchermataVisualizzaStipendio(this));
    }

    public void clickTimbraturaImpiegato() {
        Util.setSpecificScene("/com/example/progettocompleto/GestioneImpiegato/FXML/SchermataTimbraturaImpiegato.fxml", stage, c-> new SchermataTimbraturaImpiegato(this, EntityUtente.getMatricola()));
    }

    public void clickRegistra() {
        Util.setScene("/com/example/progettocompleto/GestioneImpiegati/FXML/SchermataRegistrazioneImpiegato.fxml", stage, c-> new SchermataRegistrazioneImpiegato(this));

    }

   /* public void clickDisattiva() {
        Util.setScene("/com/example/progettocompleto/GestioneImpiegati/FXML/SchermataDisattivaImpiegato.fxml", stage, c-> new SchermataDisattivaImpiegato(this));
    }*/

    public void clickTimbraturaImpiegato(int matricola) {
        Util.setSpecificScene("com/example/progettocompleto/GestioneImpiegati/FXML/SchermataTimbraturaImpiegato.fxml", stage, c-> new SchermataTimbraturaImpiegato(this, matricola));
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
                //todo popup informazione
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
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stage1,c-> new PopupErrore("Succhiami la ciolla", this,stage1));
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

    }
    @Override
    public void clickOK(){
        System.out.println("Suca");
    }
}