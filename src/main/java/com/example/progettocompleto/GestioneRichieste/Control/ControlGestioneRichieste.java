package com.example.progettocompleto.GestioneRichieste.Control;


import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.JavaMail;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Schermate.*;
import com.example.progettocompleto.Popup.PopupErrore;
import com.example.progettocompleto.Popup.PopupInformazione;
import com.example.progettocompleto.Start;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.TableRow;
import javafx.scene.control.skin.TableRowSkin;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;




public class ControlGestioneRichieste {
    private Stage stagePopup = new Stage();
    private Stage stage = Start.mainStage;
    public ControlGestioneRichieste() {
        Util.setSpecificScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml", stage, c -> new SchermataGestioneRichieste(this,Daemon.getRichieste(EntityUtente.getMatricola())));
    }
    public void clickCongedoLutto() {
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataCongedoLutto.fxml", stage, c -> new SchermataCongedoLutto(this));
    }

    public void clickRichiestaPermesso() {
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiestaPermesso.fxml", stage, c -> new SchermataRichiestaPermesso(this));
    }

    public void clickElimina(int id) throws SQLException {
        Daemon.delete(id);
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta eliminata correttamente",stagePopup));
        stagePopup.showAndWait();
        SchermataGestioneRichieste.show(this,Daemon.getRichieste(EntityUtente.getMatricola()));
    }
    public void clickInviaFerie(LocalDate dI, LocalDate dF){
       int matricola= EntityUtente.getMatricola();
        System.out.println("matricola"+matricola);
        Period periodo = Period.between(dI, dF);
        System.out.println("periodo selezionato" + periodo);
        int giorniInseriti = periodo.getDays();
        System.out.println("giorniInseriti" + giorniInseriti);
        List<LocalDate> giorniProibiti= Daemon.getGiorniProibiti();
       /* for(LocalDate date:giorniProibiti){
             if (date.isAfter(dI) && date.isBefore(dF)) {

                Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stagePopup,c->new PopupErrore("Il periodo selezionato corrisponde con i giorni proibiti",stagePopup));
                stagePopup.showAndWait();
                SchermataRichiestaFerie.show();

            }
        }*/

       int giorniFerie =Daemon.getGiorniFerie(matricola);
        System.out.println("giorni ferie"+ giorniFerie);
        if (giorniFerie>= giorniInseriti){
            System.out.println("ciaoasd");

         Daemon.insertRichiestaFerie(matricola, dI, dF);
         Daemon.updateGiorniFerie(matricola, giorniInseriti);
            Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
            stagePopup.showAndWait();
            SchermataGestioneRichieste.show(this,Daemon.getRichieste(EntityUtente.getMatricola()));
        }else{
            Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stagePopup,c->new PopupErrore("Non hai giorni sufficienti per effettuare la richiesta",stagePopup));
            stagePopup.showAndWait();
            SchermataRichiestaFerie.show();
    }

    }

    public void clickRichiestaFerie() {
        List<LocalDate> giorniPro = Daemon.getGiorniProibiti();
        System.out.println(giorniPro.size());
        List<LocalDate> dateI = new ArrayList<>();
        List<LocalDate> dateF = new ArrayList<>();
        for (int i = 0; i < giorniPro.size(); ++i) {
            if (i % 2 == 0) {

                dateI.add(giorniPro.get(i));
            } else {

                dateF.add(giorniPro.get(i));

            }

        }
        Util.setSpecificScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiestaFerie.fxml", stage, c -> new SchermataRichiestaFerie(this, dateI, dateF));
    }


    public void clickInviaPermesso(LocalDate data, String oraInizio, String oraFine)  {
        int matricola= EntityUtente.getMatricola();
        int orePermesso= Daemon.getOrePermesso(matricola);
        LocalTime inizio =LocalTime.of(Integer.parseInt(oraInizio), 00);
        LocalTime fine = LocalTime.of(Integer.parseInt(oraFine), 00);
        Duration duration = Duration.between(inizio, fine);
        int oreInserite = (int) duration.toHours();
        if(orePermesso>= oreInserite){
            Daemon.insertPermesso(data, inizio, fine, matricola);
            Daemon.updateOrePermesso(matricola, oreInserite);
            Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
            stagePopup.showAndWait();
            SchermataGestioneRichieste.show(this,Daemon.getRichieste(EntityUtente.getMatricola()));
        }else{
            Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stagePopup,c->new PopupErrore("Non hai ore sufficienti per effettuare la richiesta",stagePopup));
            stagePopup.showAndWait();
            SchermataRichiestaPermesso.show();
        }

    }

    public void clickRichiestaSciopero() {
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiestaSciopero.fxml", stage, c -> new SchermataRichiestaSciopero(this));
    }
    public void clickInviaSciopero(LocalDate data, String motivazione, String svolgimento){
        int matricola= EntityUtente.getMatricola();
      String mailDatore = Daemon.getMailDatore();
      JavaMail javaMail = new JavaMail();
        try {
            javaMail.setOggetto("Hai una richesta sciopero da parte di un impiegato");
            javaMail.setTesto("L'utente " + EntityUtente.getMatricola() + " sta richiedendo uno sciopero in data "+ data.toString()+ " con la seguente motivazione: "+ motivazione+" con il seguente svolgimento: " + svolgimento);
            JavaMail.sendMail(mailDatore);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Daemon.insertSciopero(matricola,data, motivazione, svolgimento);
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
        stagePopup.showAndWait();
        SchermataGestioneRichieste.show(this,Daemon.getRichieste(EntityUtente.getMatricola()));

        //TODO inserire la richiesta in sciopero DBMS

    }

    public void clickCongedoParentale(){
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataCongedoParentale.fxml", stage, c-> new SchermataCongedoParentale(this));
    }

    public void clickInviaParentale(LocalDate dataInizio, LocalDate dataFine, InputStream file){
        int matricola= EntityUtente.getMatricola();
        Daemon.insertCongedoParentale(matricola, dataInizio, dataFine, file);
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
        stagePopup.showAndWait();
        SchermataGestioneRichieste.show(this,Daemon.getRichieste(EntityUtente.getMatricola()));


    }

    public void clickInviaLutto(LocalDate dataInizio, LocalDate dataFine, InputStream file) {
        int matricola=EntityUtente.getMatricola();
        Daemon.insertLutto(matricola, dataInizio,dataFine,file);
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
        stagePopup.showAndWait();
        SchermataGestioneRichieste.show(this,Daemon.getRichieste(EntityUtente.getMatricola()));

    }


    public void clickRichiestaMaternita() {
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiestaMaternita.fxml", stage, c -> new SchermataRichiestaMaternita(this));
    }


    public void clickInviaMaternita(LocalDate dataInizio, LocalDate dataFine, InputStream file){
        int matricola=EntityUtente.getMatricola();
Daemon.insertMaternita(matricola, dataInizio, dataFine, file);
    }
    public void clickRichiestaMalattia() {
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiestaMalattia.fxml", stage, c -> new SchermataRichiestaMalattia(this));
       }

    public void clickInviaMalattia(LocalDate dataInizio, LocalDate dataFine, String motivazione, InputStream file) {
        int matricola = EntityUtente.getMatricola();

        Daemon.insertMalattia(matricola, dataInizio, dataFine, motivazione, file);
     /*   if (esito) {
             Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
        stagePopup.showAndWait();
        SchermataGestioneRichieste.show(this,Daemon.getRichieste(EntityUtente.getMatricola()));


        }*/
    }
public void clickRichiestaCambio(){
        int matricola= EntityUtente.getMatricola();
        List<Object> turni= Daemon.getTurni(matricola);
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiestaCambioTurno.fxml", stage, c-> new SchermataRichiestaCambioTurno(this, turni, matricola));
}

    public void clickConferma(LocalDate turnoOrigine, LocalDate turnoDestinazione, String turnoDesiderato,  String turnoPrecedente) {
        int matricola= EntityUtente.getMatricola();
        int servizio=Daemon.getServizio(matricola);
        List<Integer> matricole=Daemon.getMatricoleDestinazione(turnoDestinazione, turnoDesiderato, servizio);
        //TODO inserire l'insert nelle richieste ricevute
        for(int i=0; i<matricole.size();++i){
            System.out.println(matricole.get(i));
        }
        Daemon.insertCambioTurno(matricola, turnoOrigine, turnoDestinazione, turnoDesiderato, turnoPrecedente);

        //TODO  l'invio della mail
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
        stagePopup.showAndWait();
        SchermataGestioneRichieste.show(this,Daemon.getRichieste(EntityUtente.getMatricola()));
    }





    }
