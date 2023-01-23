package com.example.progettocompleto.GestioneImpiegati.Control;



import com.example.progettocompleto.Contenitori.Richiesta;
import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneImpiegati.Schermate.SchermataRichiesteSciopero;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControlRichiesteSciopero {
    private Stage stage = Start.mainStage;
    int matricola= EntityUtente.getMatricola();

    public void clickRichiestaSciopero() {
        List<Richiesta> richiesteSciopero= new ArrayList<>();
        richiesteSciopero= Daemon.getRichiesteSciopero(matricola);
        Util.setSpecificScene("/com/example/GestioneRemoto/GestioneImpiegati/FXML/SchermataRichiesteSciopero.fxml", stage, c-> new SchermataRichiesteSciopero(this));
    }
}

