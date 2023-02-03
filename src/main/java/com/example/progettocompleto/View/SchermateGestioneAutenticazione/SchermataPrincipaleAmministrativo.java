package com.example.progettocompleto.View.SchermateGestioneAutenticazione;



import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Controller.ControlGestioneAutenticazione.ControlLogin;
import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlGestioneImpiegati;
import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlTimbraturaRemoto;
import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlVisualizzaProfilo;
import com.example.progettocompleto.Controller.ControlGestioneProfilo.ControlVisualizzaStipendio;
import com.example.progettocompleto.Controller.ControlGestioneRichieste.ControlGestioneRichieste;
import com.example.progettocompleto.Controller.ControlGestioneTurni.ControlVisualizzaPropostaTurni;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SchermataPrincipaleAmministrativo extends SchermataPrincipaleImpiegato {


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
    public void clickLogout(ActionEvent e){
        controlLogin.clickLogout();
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
    public void clickTurnazioneTrimestrale(ActionEvent e){
        ControlVisualizzaPropostaTurni controlVisualizzaPropostaTurni= new ControlVisualizzaPropostaTurni();
    }
public static void show(){

}

}
