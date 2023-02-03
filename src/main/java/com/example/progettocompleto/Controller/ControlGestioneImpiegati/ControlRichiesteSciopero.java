package com.example.progettocompleto.Controller.ControlGestioneImpiegati;



import com.example.progettocompleto.Contenitori.Richiesta;
import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.View.SchermateGestioneImpiegati.SchermataRichiesteSciopero;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControlRichiesteSciopero {
    private Stage stage = Start.mainStage;
    private int matricola= EntityUtente.getMatricola();

    public void clickRichiestaSciopero() {
        List<Richiesta> richiesteSciopero= new ArrayList<>();
        richiesteSciopero= Daemon.getRichiesteSciopero(matricola);
        Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataRichiesteSciopero.fxml", stage, c-> new SchermataRichiesteSciopero(this));
    }
    public void clickIndietro(){
        Util.ritorno("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataGestioneImpiegati.fxml");
    }
}

