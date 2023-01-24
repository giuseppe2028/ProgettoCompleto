package com.example.progettocompleto.GestioneProfilo.Schermate;

import com.example.progettocompleto.Contenitori.Stipendio;
import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaStipendio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class SchermataVisualizzaStipendio implements Initializable {
    @FXML
    ChoiceBox <String> meseBox;
    @FXML
    Label oreLavLabel;
    @FXML
    Label gratLabel;
    @FXML
    Label oreStraoLabel;
    @FXML
    Label repLabel;
    @FXML
    Label valOrelav;
    @FXML
    Label valGrat;
    @FXML
    Label valOreStrao;
    @FXML
    Label valRep;
    @FXML
    Label stipLordo;
    @FXML
    Label trattenuteLabel;
    @FXML
    Label stipNetto;
    @FXML
    Label tredicLabel;

    @FXML
    ChoiceBox <Integer>annoBox;
private String[] mesi={"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
    List<Integer> listaAnni = new ArrayList<>();
    private ControlVisualizzaStipendio controlVisualizzaStipendio;
    private Stipendio stipendio;
    public SchermataVisualizzaStipendio(ControlVisualizzaStipendio controlVisualizzaStipendio,Stipendio stipendio){
        this.stipendio = stipendio;
        this.controlVisualizzaStipendio=controlVisualizzaStipendio;
        for (int i = 1998; i <= 2023; i++) {
            listaAnni.add(i);
        }
        int[] anni = new int[listaAnni.size()];
        for(int i = 0; i < listaAnni.size(); i++)
        {
            anni[i] = listaAnni.get(i);
        }

    }
    @Override
    public void initialize(URL arg, ResourceBundle arg1){
        meseBox.getItems().addAll(mesi);
        annoBox.getItems().addAll(listaAnni);
        annoBox.setValue(LocalDate.now().getYear());
        meseBox.setValue(convertMese(LocalDate.now().getMonthValue()));
        mostraValori(stipendio);
        meseBox.setOnAction(event->{
            mostraValori(controlVisualizzaStipendio.getResocontoStipendio(stipendio.getImpiegato(),meseBox.getValue(),annoBox.getValue()));

        });
        annoBox.setOnAction(event->{
            mostraValori(controlVisualizzaStipendio.getResocontoStipendio(stipendio.getImpiegato(),meseBox.getValue(),annoBox.getValue()));

        });

    }
  //TODO implementare la selezione e successivamente la query
 /*   public Integer selezionaAnno(ActionEvent e){
        Integer anno= annoBox.getValue();
        return anno;
    }*/
    private void mostraValori(Stipendio stipendio){
        if(stipendio == null){
            oreLavLabel.setText("");
            valOrelav.setText("");
            gratLabel.setText("");
            valGrat.setText("");
            oreStraoLabel.setText("");
            valOreStrao.setText("");
            valRep.setText("");
            tredicLabel.setText("");
            stipLordo.setText("");
            trattenuteLabel.setText("");
            stipNetto.setText("");
        }else {
            if(stipendio.isMaggiorazioneReperibilita()){
                repLabel.setText("Si");
            }else{
                repLabel.setText("No");
            }
            oreLavLabel.setText(String.valueOf(stipendio.getOreLavorate()));
            valOrelav.setText(String.valueOf(stipendio.getSaldoOreLavorate()));
            gratLabel.setText(String.valueOf(stipendio.getGratificaServizio()));
            valGrat.setText(String.valueOf(stipendio.getSalldoGratificaServizio()));
            oreStraoLabel.setText(String.valueOf(stipendio.getOreStraordinario()));
            valOreStrao.setText(String.valueOf(stipendio.getSaldoOreStraordinario()));
            valRep.setText(String.valueOf(stipendio.getSaldoMaggiorazioneReperibilita()));
            tredicLabel.setText(String.valueOf(stipendio.getSaldoTredicesima()));
            stipLordo.setText(String.valueOf(stipendio.getSaldoStipendioLordo()));
            trattenuteLabel.setText(String.valueOf(stipendio.getSaldoTrattenute()));
            stipNetto.setText(String.valueOf(stipendio.getSaldoStipendioNetto()));
        }
    }

    private String convertMese(int mese){
        switch (mese){
            case 1:
                return "Gennaio";
            case 2:
                return "Febbraio";
            case 3:
                return "Marzo";
            case 4:
                return "Aprile";
            case 5:
                return "Maggio";
            case 6:
                return "Giugno";
            case 7:
                return "Luglio";
            case 8:
                return "Agosto";
            case 9:
                return "Settembre";
            case 10:
                return "Ottobre";
            case 11:
                return "Novembre";
            case 12:
                return "Dicembre";

        }
        return null;
    }

        }



