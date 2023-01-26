package com.example.progettocompleto.GestioneAutenticazione.Schermate;



import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.GestioneAutenticazione.Control.ControlLogin;
import com.example.progettocompleto.GestioneImpiegati.Control.ControlGestioneImpiegati;
import com.example.progettocompleto.GestioneProfilo.Control.ControlTimbraturaRemoto;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaStipendio;
import com.example.progettocompleto.GestioneRichieste.Control.ControlGestioneRichieste;
import com.example.progettocompleto.GestioneTurni.Control.ControlVisualizzaPropostaTurni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SchermataPrincipaleAmministrativo extends SchermataPrincipaleImpiegato {
    @FXML
    ImageView ImmagineProfilo;
    public SchermataPrincipaleAmministrativo(ControlLogin controlLogin, List<Object> datiProfilo){
        super(controlLogin,datiProfilo);
    }

    @Override
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

    @FXML
    public void clickGestioneRichieste(ActionEvent e){
        ControlGestioneRichieste controlGestioneRichieste= new ControlGestioneRichieste();

    }
    //todo giuseppe da fare
    public void clickVisualizzaCalendario(){

    }
    @FXML
    public void clickVisualizzaProfilo(ActionEvent e){
        ControlVisualizzaProfilo controlVisualizzaProfilo=new ControlVisualizzaProfilo();



    }
    @FXML
    public void clickVisualizzaStipendio(ActionEvent e){
        ControlVisualizzaStipendio controlVisualizzaStipendio= new ControlVisualizzaStipendio();
    }
    @FXML
    public void clickGestioneImpiegati(ActionEvent e){
        ControlGestioneImpiegati controlGestioneImpiegati = new ControlGestioneImpiegati();
    }
    public void clickTimbraEntrata(ActionEvent e){
        ControlTimbraturaRemoto controlTimbraturaRemoto= new ControlTimbraturaRemoto();
    }
    @FXML
    public void clickTurnazioneTrimestrale(ActionEvent e){
        ControlVisualizzaPropostaTurni controlVisualizzaPropostaTurni= new ControlVisualizzaPropostaTurni();
    }

}
