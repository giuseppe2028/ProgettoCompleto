package com.example.progettocompleto.GestioneTurni.Control;


import com.example.progettocompleto.Contenitori.PropostaTurno;
import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneTurni.Schermate.SchermataPianificazioneTurni;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class ControlVisualizzaPropostaTurni {

public ControlVisualizzaPropostaTurni(){
    List<PropostaTurno> prostaTurni = Daemon.getPropostaTurni();
//aggiungere all'ODD questo metodo
    Util.setScene("/com/example/GestioneRemoto/GestioneTurni/FXML/Calendario.fxml",new Stage(), c-> new SchermataPianificazioneTurni(prostaTurni));
}



}

