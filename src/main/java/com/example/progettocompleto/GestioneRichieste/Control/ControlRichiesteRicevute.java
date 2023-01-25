package com.example.progettocompleto.GestioneRichieste.Control;


import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Schermate.SchermataRichiesteRicevute;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControlRichiesteRicevute {
    private Stage stage = Start.mainStage;

    public ControlRichiesteRicevute() {
        //TODO getRichiesteRicevute

        Util.setSpecificScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiesteRicevute.fxml", stage, c-> new SchermataRichiesteRicevute(this));
    }

    public void clickAccetta(int id, int refImpiegato, int matricolaDestinazione) throws SQLException {
        Daemon.accettaCambioTurno(id);
        Daemon.updateTurni(refImpiegato,matricolaDestinazione);
        // TODO: 21/01/23  pop up
    }

    public void clickRifiuta(int id) throws SQLException {
        Daemon.rifiutaCambioTurno(id);
        // TODO: 21/01/23  pop up
    }
}
