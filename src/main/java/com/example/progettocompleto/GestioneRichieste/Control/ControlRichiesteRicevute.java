package com.example.progettocompleto.GestioneRichieste.Control;


import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Schermate.SchermataRichiesteRicevute;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

public class ControlRichiesteRicevute {
    private Stage stage = Start.mainStage;

    public ControlRichiesteRicevute() {
        //TODO getRichiesteRicevute

        Util.setSpecificScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiesteRicevute.fxml", stage, c-> new SchermataRichiesteRicevute(this));
    }
}
