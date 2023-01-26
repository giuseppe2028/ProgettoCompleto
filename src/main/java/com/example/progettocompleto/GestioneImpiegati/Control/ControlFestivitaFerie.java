package com.example.progettocompleto.GestioneImpiegati.Control;



import com.example.progettocompleto.FileDiSistema.ControlInterface;
import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.GestioneImpiegati.Schermate.SchermataFestivitaFerie;

import com.example.progettocompleto.Contenitori.Periodi;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.Popup.PopupErrore;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ControlFestivitaFerie implements ControlInterface {
    private List<Periodi> periodi;
    Stage stage = Start.mainStage;
    public ControlFestivitaFerie(){
        periodi = Daemon.getPeriodi();
        Util.setSpecificScene("/com/example/progettocompleto/GestioneImpiegati/FXML/SchermataFesitivitaFerie.fxml", stage, c->new SchermataFestivitaFerie(this,periodi));
    }


    public void clickInvia(LocalDate dataInizio, LocalDate dataFine, String categoria) throws SQLException {
        Stage stagePopup=new Stage();
        if(Daemon.verifyDateProibite(dataInizio, dataFine, categoria)){
            Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stagePopup, c-> new PopupErrore("Le date da lei inserite sono già state registrate",this,stagePopup ));
        }else {
            Daemon.insertDateProibite(dataInizio, dataFine, categoria);
//todo popup inf.
        }
    }

    public void clickIndietro(String s) {
        Util.ritorno(s);
    }

    @Override
    public void clickOK(){
        System.out.println("ragù");
    }
}
