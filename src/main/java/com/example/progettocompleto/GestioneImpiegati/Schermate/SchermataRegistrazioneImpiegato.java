package com.example.progettocompleto.GestioneImpiegati.Schermate;


import com.example.progettocompleto.FileDiSistema.CodiceFiscaleCalculator;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneImpiegati.Control.ControlGestioneImpiegati;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.time.LocalDate;

public class SchermataRegistrazioneImpiegato {
    @FXML
    private TextField nome,cognome,mailPersonale,IBAN,recapito;
    @FXML
    private TextField indirizzoResidenza;
    @FXML
    private ChoiceBox<String> ruolo;
    @FXML
    private ChoiceBox<Integer> servizio;
    private char sesso;
    @FXML
    private CheckBox reperibile;
    @FXML
    private RadioButton maschio,femmina;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private Label codiceFiscale;
    @FXML
    private TextField luogoNascita;

    private String nomeTesto,cognomeTesto,luogoNascitaTesto;
    private LocalDate dataNascitaTesto;
    private boolean sessoBool;
    ToggleGroup toggleGroup = new ToggleGroup();
    ControlGestioneImpiegati controlGestioneImpiegati;
    CodiceFiscaleCalculator codiceFiscaleCalculator;
    public SchermataRegistrazioneImpiegato(ControlGestioneImpiegati controlGestioneImpiegati){
        this.controlGestioneImpiegati=controlGestioneImpiegati;
        codiceFiscaleCalculator = new CodiceFiscaleCalculator();
        }
        @FXML
        public void initialize(){

            servizio.getItems().add(1);
            servizio.getItems().add(2);
            servizio.getItems().add(3);
            servizio.getItems().add(4);
            ruolo.getItems().add("Impiegato");
            ruolo.getItems().add("Amministrativo");

        }
        @FXML
        public void selezionaSessoMaschio(ActionEvent e){
            femmina.setSelected(false);
            sessoBool = true;
            sesso = 'M';
            System.out.println(sesso);
        }
    @FXML
    public void selezionaSessoFemmina(ActionEvent e){
        maschio.setSelected(false);
        sessoBool = false;
        sesso = 'F';
        System.out.println(sesso);
    }
        public void compilaDatiProfilo(){
            controlGestioneImpiegati.compila(nome.getText(),cognome.getText(),Long.parseLong(recapito.getText()),mailPersonale.getText(),indirizzoResidenza.getText(),IBAN.getText(),servizio.getValue(),ruolo.getValue(),sesso,reperibile.isSelected(),dataPicker.getValue(),cf);
    }
    String cf;
    public void calculate(){
        nomeTesto = nome.getText();
        cognomeTesto = cognome.getText();
        dataNascitaTesto = dataPicker.getValue();
        luogoNascitaTesto = luogoNascita.getText();
        cf = codiceFiscaleCalculator.computeCodiceFiscale(nomeTesto,cognomeTesto,dataNascitaTesto,sessoBool, luogoNascitaTesto);
        codiceFiscale.setText(cf);
    }
    public void clickIndietro(ActionEvent e){
        Util.ritorno("/com/example/GestioneRemoto/GestioneImpiegato/FXML/SchermataGestioneImpiegati.fxml");
    }
    }

