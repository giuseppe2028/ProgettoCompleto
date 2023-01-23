package com.example.progettocompleto.GestioneImpiegati.Control;



import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.GestioneImpiegati.Schermate.SchermataFestivitaFerie;

import com.example.progettocompleto.Contenitori.Periodi;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.util.List;

public class ControlFestivitaFerie {
    private List<Periodi> periodi;
    Stage stage = Start.mainStage;
    public ControlFestivitaFerie(){
        periodi = Daemon.getPeriodi();

        Util.setSpecificScene("/com/example/progettocompleto/GestioneImpiegato/FXML/SchermataFesitivitaFerie.fxml", stage, c->new SchermataFestivitaFerie(this,periodi));
    }
}
