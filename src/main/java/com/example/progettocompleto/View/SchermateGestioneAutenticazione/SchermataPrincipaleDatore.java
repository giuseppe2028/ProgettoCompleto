package com.example.progettocompleto.View.SchermateGestioneAutenticazione;

import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlVisualizzaCalendario;
import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Timer.Timer;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Controller.ControlGestioneAutenticazione.ControlLogin;
import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlFestivitaFerie;
import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlGestioneImpiegati;
import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlVisualizzaProfilo;
import com.example.progettocompleto.Start;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private java.util.Timer timer = new java.util.Timer();
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
    public void clickLogout(ActionEvent e){
        controlLogin.clickLogout();
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
    public static void show(){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/SchermataPrincipaleDatore.fxml",Start.mainStage, c-> new SchermataPrincipaleDatore(new ControlLogin(),(ArrayList<Object>) EntityUtente.getDatiProfilo()));
    }
}

