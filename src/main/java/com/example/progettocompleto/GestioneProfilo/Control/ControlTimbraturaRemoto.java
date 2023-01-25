package com.example.progettocompleto.GestioneProfilo.Control;



import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneProfilo.Schermate.SchermataTimbraturaRemoto;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ControlTimbraturaRemoto {
    Stage stage= Start.mainStage;
    int matricola;


    public  ControlTimbraturaRemoto(){

        Util.setSpecificScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataTimbraturaRemoto.fxml", stage, c-> new SchermataTimbraturaRemoto(this));
    }

    public void clickConferma(LocalDate data, LocalTime orario,String motivazione ) throws SQLException {
        matricola= EntityUtente.getMatricola();
        Boolean es= Daemon.controlloTimbr(data, matricola);
        if (es) {
            Boolean esito = Daemon.verifyTimbratura(data, orario, matricola);
            if (esito) {
                //todo popup errore
            } else {
                String tipoTurno= Daemon.getTurno(data, matricola);
                Daemon.insertTimbratura(data, orario, matricola, tipoTurno, motivazione);
                //todo popup informazione
            }
        }else {
            //todo popup errore
        }
    }
}
