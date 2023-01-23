package com.example.progettocompleto.GestioneProfilo.Control;


import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneProfilo.Schermate.SchermataVisualizzaStipendio;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

public class ControlVisualizzaStipendio {
    private Stage stage = Start.mainStage;
    public void clickVisualizzaStipendio() {
        SchermataVisualizzaStipendio schermataVisualizzaStipendio= Util.setSpecificScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataVisualizzaStipendio.fxml", stage, c-> new SchermataVisualizzaStipendio(this) );
    }
}

