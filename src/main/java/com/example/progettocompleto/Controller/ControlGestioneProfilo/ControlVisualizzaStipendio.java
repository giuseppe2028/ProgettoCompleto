package com.example.progettocompleto.Controller.ControlGestioneProfilo;


import com.example.progettocompleto.View.SchermateGestioneProfilo.SchermataVisualizzaStipendio;

import com.example.progettocompleto.Contenitori.Stipendio;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Start;
import com.example.progettocompleto.Model.Daemon;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ControlVisualizzaStipendio {
    private Stage stage = Start.mainStage;
    public ControlVisualizzaStipendio(){
        int matricola = EntityUtente.getMatricola();
        Stipendio stipendio =  Daemon.getStipendio(matricola, convertMese(LocalDate.now().getMonthValue()),LocalDate.now().getYear());
        Util.setScene("/com/example/progettocompleto/FXML/GestioneProfilo/SchermataVisualizzaStipendio.fxml",stage, c->new SchermataVisualizzaStipendio(this,stipendio));
    }
    private String convertMese(int mese){
        switch (mese){
            case 1:
                return "Gennaio";
            case 2:
                return "Febbraio";
            case 3:
                return "Marzo";
            case 4:
                return "Aprile";
            case 5:
                return "Maggio";
            case 6:
                return "Giugno";
            case 7:
                return "Luglio";
            case 8:
                return "Agosto";
            case 9:
                return "Settembre";
            case 10:
                return "Ottobre";
            case 11:
                return "Novembre";
            case 12:
                return "Dicembre";

        }
        return null;
    }
    public Stipendio getResocontoStipendio(int matricola,String mese, int anno){
        return Daemon.getStipendio(matricola,mese,anno);
    }
}
