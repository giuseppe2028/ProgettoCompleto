package com.example.progettocompleto.GestioneAutenticazione.Schermate;

import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.DatePicker;
import com.example.progettocompleto.GestioneAutenticazione.Control.ControlLogin;
import com.example.progettocompleto.GestioneImpiegati.Control.ControlFestivitaFerie;
import com.example.progettocompleto.GestioneImpiegati.Control.ControlGestioneImpiegati;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import com.example.progettocompleto.Start;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SchermataPrincipaleDatore implements Runnable{
    private List<Object> lista;
    private ControlLogin controlLogin;
    @FXML
    private Label nomeCognome;
    @FXML
    private Label matricola;
    @FXML
    private Label ruolo;
    @FXML
    private Label dataCorrente;
    @FXML
    private ImageView immagineView;
    private Timer timer = new Timer();
    public SchermataPrincipaleDatore(ControlLogin controlLogin, ArrayList<Object> lista){
        this.controlLogin = controlLogin;
        this.lista = lista;
    }
    @FXML
    public void initialize() throws IOException {
        String stringNomeCognome =lista.get(1).toString() +" " +  lista.get(2).toString();
        nomeCognome.setText(stringNomeCognome);
        String stringMatricola =  lista.get(0).toString();
        String stringRuolo = lista.get(6).toString();
        nomeCognome.setText(stringNomeCognome);
        matricola.setText(stringMatricola);
        ruolo.setText(stringRuolo);
        Image im;
        InputStream is= Daemon.getFotoProfilo((Integer) lista.get(0));
        im = new Image(is);
        immagineView.setImage(im);
        is.close();

    }
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
    public void clickLogout(ActionEvent e){
        controlLogin.clickLogout(e);
    }

    @FXML
    public void clickVisualizzaProfilo(ActionEvent e){
        ControlVisualizzaProfilo controlVisualizzaProfilo=new ControlVisualizzaProfilo();


    }
    @FXML
    public void clickGestioneFestivitaFerie(ActionEvent e){
        ControlFestivitaFerie controlFestivitaFerie= new ControlFestivitaFerie();

    }

    @FXML
    public void clickGestioneImpiegati(ActionEvent e){
        ControlGestioneImpiegati controlGestioneImpiegati = new ControlGestioneImpiegati();

    }
}

