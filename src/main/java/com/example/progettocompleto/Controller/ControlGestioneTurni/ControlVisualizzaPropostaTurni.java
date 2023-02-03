package com.example.progettocompleto.Controller.ControlGestioneTurni;


import com.example.progettocompleto.Contenitori.PropostaTurno;

import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Start;
import com.example.progettocompleto.View.Popup.PopupConferma;
import com.example.progettocompleto.View.Popup.PopupInformazione;

import com.example.progettocompleto.View.SchermateGestioneTurni.SchermataPianificazioneTurni;
import javafx.stage.Stage;

import java.util.List;



public class ControlVisualizzaPropostaTurni {

    private Stage stagePopup = new Stage();
    private List<PropostaTurno> proposta;
    private Stage stage = Start.mainStage;


    public ControlVisualizzaPropostaTurni(){
        List<PropostaTurno> prostaTurni = Daemon.getPropostaTurni();
//aggiungere all'ODD questo metodo
        Util.setScene("/com/example/progettocompleto/FXML/GestioneTurni/Calendario.fxml",stage, c-> new SchermataPianificazioneTurni(prostaTurni));
    }

    public void clickAccetta(){
        Util.setScenePopup("/com/example/progettocompleto/PopUp/FXML/PopUpConferma.fxml", stagePopup, c-> new PopupConferma("Sei sicuro della scelta?", stagePopup ));
        stagePopup.showAndWait();
        Daemon.getPropostaTurni();
        Daemon.insertTurni(proposta);
        Util.setScenePopup("/com/example/progettocompleto/PopUp/FXML/PopUpInformazione.fxml", stagePopup, c-> new PopupInformazione("Proposta di turnazione accettata", stagePopup));
        stagePopup.showAndWait();
        //SchermataPrincipaleAmministrativo.show();

    }

    public void clickRifiuta(){
        Util.setScenePopup("/com/example/progettocompleto/PopUp/FXML/PopUpConferma.fxml", stagePopup, c-> new PopupConferma("Sei sicuro della scelta?", stagePopup ));
        stagePopup.showAndWait();
        Util.setScenePopup("/com/example/progettocompleto/PopUp/FXML/PopUpInformazione.fxml", stagePopup, c-> new PopupInformazione("Proposta di turnazione accettata", stagePopup));
        stagePopup.showAndWait();

        Daemon.deletePropostaTurni();
        Daemon.getMatricole();


    }



}

