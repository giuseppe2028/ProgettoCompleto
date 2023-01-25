package com.example.progettocompleto.GestioneAutenticazione.Schermate;

import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.DatePicker;
import com.example.progettocompleto.GestioneAutenticazione.Control.ControlLogin;
import com.example.progettocompleto.GestioneProfilo.Control.ControlTimbraturaRemoto;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaStipendio;
import com.example.progettocompleto.GestioneRichieste.Control.ControlGestioneRichieste;
import com.example.progettocompleto.Start;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


//TODO implementare la classe schermata Principale Dipendente
public class SchermataPrincipaleImpiegato implements Runnable {

    List<Object> datiProfilo;
    @FXML
    Label nomeCognome;
    @FXML
    Label matricola;
    @FXML
    Label ruolo;

    ControlLogin controlLogin;

    public SchermataPrincipaleImpiegato(ControlLogin controlLogin, List<Object> datiProfilo){

        this.datiProfilo = datiProfilo;
        this.controlLogin = controlLogin;
    }

    @FXML
    ImageView Iconlogout;
    @FXML
    Label dataCorrente;
    @FXML
    private ImageView ImmagineProfilo;
    @FXML
    private AnchorPane bottone;

    @FXML
    private Button logout;
    @FXML
    private AnchorPane rettangoloUP;
    @FXML
    private AnchorPane sfondoDropDownButton;
    @FXML
    MenuButton menuButtonGestioneProfilo = new MenuButton();
    @FXML
    MenuButton menuButtonGestioneTimbratura = new MenuButton();
    Stage stage = Start.mainStage;
    private  Timer timer = new Timer();
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
        InputStream is= Daemon.getFotoProfilo((Integer)  datiProfilo.get(11));
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
                int secondo = DatePicker.getSecond();
                int minuto = DatePicker.getMinute();
                int ora = DatePicker.getHour();
                int giorno = DatePicker.getDay();
                int mese = DatePicker.getMonth();
                int anno = DatePicker.getYear();
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
    public void clickGestioneProfilo(ActionEvent e){

    }@FXML
    public void clickGestioneTimbratura(){

    }
    @FXML
    public void clickDropDownButton(ActionEvent e){

    }
    @FXML
    public void clickVisualizzaProfilo(ActionEvent e){
        ControlVisualizzaProfilo controlVisualizzaProfilo=new ControlVisualizzaProfilo();



    }
    @FXML
    public void clickLogout(ActionEvent e){
        controlLogin.clickLogout(e);
    }
    @FXML
    public void clickVisualizzaCalendario(ActionEvent e){

    }
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

}

