package com.example.progettocompleto.View.SchermateGestioneAutenticazione;

import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlVisualizzaCalendario;
import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Timer.Timer;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Controller.ControlGestioneAutenticazione.ControlLogin;
import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlTimbraturaRemoto;
import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlVisualizzaProfilo;
import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlVisualizzaStipendio;
import com.example.progettocompleto.Controller.ControlGestioneRichieste.ControlGestioneRichieste;
import com.example.progettocompleto.Start;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
public class SchermataPrincipaleImpiegato implements Runnable {
    List<Object> datiProfilo;
    @FXML
    Label nomeCognome;
    @FXML
    Label matricola;
    @FXML
    Label ruolo;
    ControlLogin controlLogin;
    @FXML
    Label dataCorrente;
    @FXML
    ImageView ImmagineProfilo;
    Stage stage = Start.mainStage;
    private java.util.Timer timer = new java.util.Timer();
    public SchermataPrincipaleImpiegato(ControlLogin controlLogin, List<Object> datiProfilo){
        this.datiProfilo = datiProfilo;
        this.controlLogin = controlLogin;
    }

    @FXML
    public void initialize() throws IOException {

        String stringNomeCognome =  datiProfilo.get(1).toString() +" "+ datiProfilo.get(2).toString();
        nomeCognome.setText(stringNomeCognome);
        String stringMatricola = datiProfilo.get(0).toString();
        String stringRuolo =datiProfilo.get(6).toString();
        nomeCognome.setText(stringNomeCognome);
        matricola.setText(stringMatricola);
        ruolo.setText(stringRuolo);
        Image im;
        InputStream is= Daemon.getFotoProfilo(EntityUtente.getMatricola());
        im = new Image(is);
        ImmagineProfilo.setImage(im);
        is.close();



    }
    @Override
    public void run() {
        int x = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String oraCorrente;
                int secondo = Timer.getSecond();
                int minuto = Timer.getMinute();
                int ora = Timer.getHour();
                int giorno = Timer.getDay();
                int mese = Timer.getMonth();
                int anno = Timer.getYear();
                String input = giorno+"-"+mese+"-"+anno+"T"+ora + ":" + minuto +":" + secondo;
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
                try {
                    Date date = inputFormat.parse(input);
                    DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    oraCorrente = outputFormat.format(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(()->{
                    dataCorrente.setText(oraCorrente);

                });
            }
        },0,1000);

    }

    @FXML
    public void clickVisualizzaProfilo(ActionEvent e){
        ControlVisualizzaProfilo controlVisualizzaProfilo=new ControlVisualizzaProfilo();
    }
    @FXML
    public void clickLogout(ActionEvent e){
        controlLogin.clickLogout();
    }
    //todo da implementare giuseppe
   /* @FXML
    public void clickVisualizzaCalendario(ActionEvent e){
        System.out.println("como");
        controlLogin.clickVisualizzaCalendario();
    }
    */
    @FXML
    public void clickGestioneRichieste(ActionEvent e){
        ControlGestioneRichieste controlGestioneRichieste= new ControlGestioneRichieste();
    }

    @FXML
    public void clickVisualizzaStipendio(ActionEvent e){
        ControlVisualizzaStipendio controlVisualizzaStipendio= new ControlVisualizzaStipendio();

    }

    public void clickTimbraEntrata(ActionEvent e){
        ControlTimbraturaRemoto controlTimbraturaRemoto= new ControlTimbraturaRemoto();

    }
    @FXML
    public void clickVisualizzaCalendario(){
        System.out.println("qui");
    ControlVisualizzaCalendario controlVisualizzaCalendario = new ControlVisualizzaCalendario();
}



}

