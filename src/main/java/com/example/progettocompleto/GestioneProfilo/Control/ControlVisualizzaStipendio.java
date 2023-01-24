package com.example.progettocompleto.GestioneProfilo.Control;


import com.example.progettocompleto.GestioneProfilo.Schermate.SchermataVisualizzaStipendio;

import com.example.progettocompleto.Contenitori.Stipendio;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.Start;
import com.example.progettocompleto.FileDiSistema.Daemon;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ControlVisualizzaStipendio {
    private String mese;
    private int anno;
    private Stage stage = Start.mainStage;
    private SchermataVisualizzaStipendio schermataVisualizzaStipendio;
    public ControlVisualizzaStipendio(){
        int matricola = EntityUtente.getMatricola();
        System.out.println(convertMese(LocalDate.now().getMonthValue()));

        Stipendio stipendio =  Daemon.getStipendio(matricola, convertMese(LocalDate.now().getMonthValue()),LocalDate.now().getYear());
        System.out.println(stipendio.toString());
        Util.setScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataVisualizzaStipendio.fxml",stage, c->new SchermataVisualizzaStipendio(this,stipendio));
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
