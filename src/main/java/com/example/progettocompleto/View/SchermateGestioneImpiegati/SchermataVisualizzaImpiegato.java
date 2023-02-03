package com.example.progettocompleto.View.SchermateGestioneImpiegati;

import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlGestioneImpiegati;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class SchermataVisualizzaImpiegato  {
    @FXML
    private ImageView ImmagineProfilo;
    @FXML
    private Label matricolaLabel;
    @FXML
    private Label mailLabel;
    @FXML
    private Label dataNascitaLabel;
    @FXML
    private Label cfLabel;
    @FXML
    private Label nomeLabel;

    @FXML
    private Label cognomeLabel;
    @FXML
    private Label RecapitoLabel;
    @FXML
    private Label IndirizzoLabel;
    @FXML
    private Label RuoloLabel;
    @FXML
    private Label IbanLabel;
    @FXML
    private Label servizioLabel;
    private List<Object> imp;
    private InputStream im;
    private ControlGestioneImpiegati controlGestioneImpiegati;
    public SchermataVisualizzaImpiegato(ControlGestioneImpiegati controlGestioneImpiegati, List<Object> imp, InputStream im){
        this.controlGestioneImpiegati=controlGestioneImpiegati;
        this.imp=imp;
        this.im=im;
    }


    public void initialize() throws IOException {
        for(int i=0; i< imp.size();++i){
            System.out.println(imp.get(i));
        }
        String matricola= String.valueOf(imp.get(0));
        matricolaLabel.setText(matricola);
        nomeLabel.setText(imp.get(1).toString());
        cognomeLabel.setText(imp.get(2).toString());
        cfLabel.setText(imp.get(3).toString());
        dataNascitaLabel.setText(imp.get(4).toString());
        IndirizzoLabel.setText(imp.get(5).toString());
        RuoloLabel.setText(imp.get(6).toString());
        IbanLabel.setText(imp.get(8).toString());
        RecapitoLabel.setText(imp.get(9).toString());
        mailLabel.setText(imp.get(10).toString());
        Image is;
        is= new Image(im);
        ImmagineProfilo.setImage(is);
        im.close();
//mailLabel.setText((String) imp.get(12));

        servizioLabel.setText(imp.get(12).toString());

    }
    @FXML
    public void clickIndietro(ActionEvent e){
        controlGestioneImpiegati.clickIndietro("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataGestioneImpiegati.fxml");
    }
}
