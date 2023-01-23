package com.example.progettocompleto.GestioneImpiegati.Schermate;

import com.example.progettocompleto.GestioneImpiegati.Control.ControlGestioneImpiegati;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.List;


public class SchermataVisualizzaImpiegato  {
    @FXML
    Label matricolaLabel;
    @FXML
    Label mailLabel;
    @FXML
    Label dataNascitaLabel;
    @FXML
    Label cfLabel;
    @FXML
    Label nomeLabel;

    @FXML
    Label cognomeLabel;
    @FXML
    Label RecapitoLabel;
    @FXML
    Label IndirizzoLabel;
    @FXML
    Label RuoloLabel;
    @FXML
    Label IbanLabel;
    @FXML
    Label servizioLabel;
    List<Object> imp;
    ControlGestioneImpiegati controlGestioneImpiegati;
    public SchermataVisualizzaImpiegato(ControlGestioneImpiegati controlGestioneImpiegati, List<Object> imp){
        this.controlGestioneImpiegati=controlGestioneImpiegati;
        this.imp=imp;
    }


    public void initialize() {
        for(int i=0; i< imp.size();++i){
            System.out.println(imp.get(i));
        }
        String matricola= String.valueOf(imp.get(0));
        matricolaLabel.setText(matricola);
        nomeLabel.setText(imp.get(1).toString());
        cognomeLabel.setText(imp.get(2).toString());
        cfLabel.setText(imp.get(3).toString());
        dataNascitaLabel.setText(imp.get(4).toString());
        IndirizzoLabel.setText(imp.get(6).toString());
        RuoloLabel.setText(imp.get(7).toString());
        IbanLabel.setText(imp.get(10).toString());
        RecapitoLabel.setText(imp.get(11).toString());
//mailLabel.setText((String) imp.get(12));

        servizioLabel.setText(imp.get(12).toString());

    }
}
