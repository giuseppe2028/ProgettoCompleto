package com.example.progettocompleto.Controller.ControlGestioneProfilo;

import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Start;
import com.example.progettocompleto.View.SchermateGestioneProfilo.SchermataVisualizzaCalendario;
import javafx.stage.Stage;

public class ControlVisualizzaCalendario {
    private Stage stage = Start.mainStage;
    public ControlVisualizzaCalendario(){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneProfilo/VisualizzaCalendario.fxml",stage,c->new SchermataVisualizzaCalendario());
    }
}
