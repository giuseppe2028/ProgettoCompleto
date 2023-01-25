package com.example.progettocompleto.GestioneRichieste.Control;


import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Schermate.*;
import com.example.progettocompleto.Start;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.scene.control.skin.TableRowSkin;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;




public class ControlGestioneRichieste {
int matricola;
    public void clickCongedoLutto() {
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataCongedoLutto.fxml", stage, c -> new SchermataCongedoLutto(this));
    }

    public void clickRichiestaPermesso() {
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiestaPermesso.fxml", stage, c -> new SchermataRichiestaPermesso(this));
    }

    public void clickElimina(int id) throws SQLException {
        //TODO popup conferma
        Daemon.delete(id);
        //TODO popup info.
    }


    public class CustomTableRowSkin<T> extends TableRowSkin<T> {
        public CustomTableRowSkin(TableRow<T> tableRow) {
            super(tableRow);
        }

    }

    private Stage stage = Start.mainStage;

    public ControlGestioneRichieste() {

        Util.setSpecificScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml", stage, c -> new SchermataGestioneRichieste(this));
    }



    public void clickInviaFerie(LocalDate dI, LocalDate dF) throws SQLException {
       int matricola= EntityUtente.getMatricola();
        Period periodo = Period.between(dI, dF);
        int giorniInseriti = periodo.getDays();
        List<LocalDate> giorniProibiti= Daemon.getGiorniProibiti();
        for(LocalDate date: giorniProibiti){
             if (date.isAfter(dI) && date.isBefore(dF)) {
             Alert a= new Alert(Alert.AlertType.ERROR);
             a.setContentText("Il periodo selezionato corrisponde con i giorni proibiti");
             a.showAndWait();
            }
        }

       int giorniFerie =Daemon.getGiorniFerie(matricola);
        if (giorniFerie>= giorniInseriti){
         Daemon.insertRichiestaFerie(matricola, dI, dF);
         Daemon.updateGiorniFerie(matricola, giorniInseriti);
         Alert a= new Alert(Alert.AlertType.INFORMATION);
         a.setContentText("Richiesta accettata");
         a.showAndWait();
        }else{
         Alert a= new Alert(Alert.AlertType.ERROR);
        a.setContentText("Non hai giorni sufficienti per effettuare la richiesta!");
        a.showAndWait();
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


   public void clickInviaPermesso(LocalDate data, String oraInizio, String minutiInizio, String oraFine, String minutiFine) throws SQLException {
        int matricola= EntityUtente.getMatricola();
        //TODO aggiungere i controlli sulla data
        int orePermesso= Daemon.getOrePermesso(matricola);
        LocalDateTime inizio = LocalDateTime.of(data, LocalTime.of(Integer.parseInt(oraInizio), Integer.parseInt(minutiInizio)));
        LocalDateTime fine = LocalDateTime.of(data, LocalTime.of(Integer.parseInt(oraFine), Integer.parseInt(minutiFine)));
        Duration duration = Duration.between(inizio, fine);
        //non minuti ma ore
        long minutiInseriti = duration.toMinutes();
        System.out.println("I minuti inseriti sono: " + minutiInseriti);
        if(orePermesso>= minutiInseriti){
        //TODO inserire l'insert richiesta e l'update
            Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Richiesta accettata");
            a.showAndWait();
        }else{
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("Non hai ore sufficienti per effettuare la richiesta!");
            a.showAndWait();
        }

    }

    public void clickRichiestaSciopero() {
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiestaSciopero.fxml", stage, c -> new SchermataRichiestaSciopero(this));
    }
    public void clickInviaSciopero(LocalDate data, String motivazione, String svolgimento){
        int matricola= EntityUtente.getMatricola();
      //int matricolaDatore=  Daemon.getMatricolaDatore();
      //TODO implementare l'invio della mail al datore
        Daemon.insertSciopero(matricola,data, motivazione, svolgimento);
        Alert a= new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Richiesta inoltrata");
        a.showAndWait();
        //TODO inserire la richiesta in sciopero DBMS

    }

    public void clickCongedoParentale(){
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataCongedoParentale.fxml", stage, c-> new SchermataCongedoParentale(this));
    }

    public void clickInviaParentale(LocalDate dataInizio, LocalDate dataFine, InputStream file){
        int matricola= EntityUtente.getMatricola();
        Daemon.insertCongedoParentale(matricola, dataInizio, dataFine, file);
        //TODO inserire nel DBMS la richiesta ed il popup

    }

    public void clickInviaLutto(LocalDate dataInizio, LocalDate dataFine, InputStream file) {
        int matricola=EntityUtente.getMatricola();
        Daemon.insertLutto(matricola, dataInizio,dataFine,file);
        //TODO inserire il popup
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
            // TODO: 20/01/23 inserire il popup richiesta effettuata

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

        //TODO  l'invio della mail ed il popup inform.
    }





    }
