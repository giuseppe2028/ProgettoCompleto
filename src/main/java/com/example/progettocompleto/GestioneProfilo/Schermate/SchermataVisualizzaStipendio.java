package com.example.progettocompleto.GestioneProfilo.Schermate;

import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaStipendio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
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
    ControlVisualizzaStipendio controlVisualizzaStipendio;
    public SchermataVisualizzaStipendio(ControlVisualizzaStipendio controlVisualizzaStipendio){
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

    }
  //TODO implementare la selezione e successivamente la query
 /*   public Integer selezionaAnno(ActionEvent e){
        Integer anno= annoBox.getValue();
        return anno;
    }*/
        }



