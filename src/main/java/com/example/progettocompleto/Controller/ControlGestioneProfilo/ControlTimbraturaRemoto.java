package com.example.progettocompleto.Controller.ControlGestioneProfilo;



import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.View.SchermateGestioneProfilo.SchermataTimbraturaRemoto;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ControlTimbraturaRemoto {
    private Stage stage= Start.mainStage;
    private int matricola;


    public  ControlTimbraturaRemoto(){

        SchermataTimbraturaRemoto schermataTimbraturaRemoto = Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneProfilo/SchermataTimbraturaRemoto.fxml", stage, c-> new SchermataTimbraturaRemoto(this));
        Thread thread = new Thread(schermataTimbraturaRemoto);
        thread.start();
    }

    public void clickConferma(LocalDate data, LocalTime orario,String motivazione ) throws SQLException {
        matricola= EntityUtente.getMatricola();
        Boolean es= Daemon.controlloTurno(data, matricola);
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
