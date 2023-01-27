package com.example.progettocompleto.GestioneProfilo.Schermate;


import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchermataVisualizzaProfilo {
    @FXML
    private ImageView ImmagineProfilo;
    @FXML
    private Label nomeLabel;
    @FXML
    private Label cognomeLabel;
    @FXML
    private Label matricolaLabel;
    @FXML
    private Label dataNascitaLabel;
    @FXML
    private Label mailLabel;

    @FXML
    private Label cfLabel;
    @FXML
    private Label recapitoLabel;
    @FXML
    private Label indirizzoLabel;
    @FXML
    private Label ruoloLabel;
    @FXML
    private Label ibanLabel;
    @FXML
    private Label mailPLabel;
    private ControlVisualizzaProfilo controlVisualizzaProfilo;
    private static ArrayList<Object> datiProfilo;

    public SchermataVisualizzaProfilo(ControlVisualizzaProfilo controlVisualizzaProfilo, ArrayList<Object> datiProfilo) {
        this.controlVisualizzaProfilo = controlVisualizzaProfilo;
        this.datiProfilo = datiProfilo;

    }

    @FXML
    public void initialize() throws SQLException, IOException {

        String matricola = datiProfilo.get(0).toString();
        matricolaLabel.setText(matricola);
        nomeLabel.setText(datiProfilo.get(1).toString());
        cognomeLabel.setText(datiProfilo.get(2).toString());
        cfLabel.setText(datiProfilo.get(3).toString());
        String data = datiProfilo.get(4).toString();
        dataNascitaLabel.setText(data);
        indirizzoLabel.setText(datiProfilo.get(5).toString());
        ruoloLabel.setText(datiProfilo.get(6).toString());
        mailLabel.setText(datiProfilo.get(7).toString());
        ibanLabel.setText(datiProfilo.get(8).toString());
        String recapito = datiProfilo.get(9).toString();
        recapitoLabel.setText(recapito);
        mailPLabel.setText(datiProfilo.get(10).toString());
        Image im;
        InputStream is = Daemon.getFotoProfilo((Integer) datiProfilo.get(0));
        im = new Image(is);
        ImmagineProfilo.setImage(im);
        is.close();

    }

    public void clickModificaPassword(){
controlVisualizzaProfilo.clickModificaPassword();
    }
   public void clickIndietro(ActionEvent e){
           if(datiProfilo.get(6).equals("Datore")){
              controlVisualizzaProfilo.clickIndietro("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataPrincipaleDatore.fxml");
           }
           else if(datiProfilo.get(6).equals("Impiegato")){
               controlVisualizzaProfilo.clickIndietro("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataPrincipaleImpiegato.fxml");
           }
           else{
               controlVisualizzaProfilo.clickIndietro("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataPrincipaleAmministrativo.fxml");
           }
   }
        public void clickModifica(ActionEvent event){
            controlVisualizzaProfilo.clickModifica();
        }
        public static void show(List<Object> datiProfiloAggiornati){
            Util.setScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataVisualizzaProfilo.fxml", Start.mainStage,c->new SchermataVisualizzaProfilo(new ControlVisualizzaProfilo(),(ArrayList<Object>) datiProfiloAggiornati));
        }
    }
