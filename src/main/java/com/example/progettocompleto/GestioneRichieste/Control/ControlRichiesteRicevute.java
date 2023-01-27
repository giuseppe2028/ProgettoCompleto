package com.example.progettocompleto.GestioneRichieste.Control;


import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Schermate.SchermataGestioneRichieste;
import com.example.progettocompleto.GestioneRichieste.Schermate.SchermataRichiesteRicevute;
import com.example.progettocompleto.Popup.PopupInformazione;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControlRichiesteRicevute {
    private Stage stagePopup = new Stage();
    private Stage stage = Start.mainStage;

    public ControlRichiesteRicevute() {
        //TODO getRichiesteRicevute

        Util.setSpecificScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataRichiesteRicevute.fxml", stage, c-> new SchermataRichiesteRicevute(this));
    }

    public void clickAccetta(int id, int refImpiegato, int matricolaDestinazione) throws SQLException {
        Daemon.accettaCambioTurno(id);
        Daemon.updateTurni(refImpiegato,matricolaDestinazione);
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
        stagePopup.showAndWait();
        SchermataRichiesteRicevute.show(this);

    }

    public void clickRifiuta(int id) throws SQLException {
        Daemon.rifiutaCambioTurno(id);
        Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Richiesta Accettata",stagePopup));
        stagePopup.showAndWait();
        SchermataRichiesteRicevute.show(this);
    }
}
