package com.example.progettocompleto.Controller.ControlGestioneProfilo;



import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.View.SchermateGestioneProfilo.SchermataModificaPassword;
import com.example.progettocompleto.View.SchermateGestioneProfilo.SchermataModificaProfilo;
import com.example.progettocompleto.View.SchermateGestioneProfilo.SchermataVisualizzaProfilo;
import com.example.progettocompleto.View.Popup.PopupErrore;
import com.example.progettocompleto.View.Popup.PopupInformazione;
import com.example.progettocompleto.Start;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlVisualizzaProfilo {
    private Stage stagePopup = new Stage();
    private Stage stage = Start.mainStage;
    private List<Object> datiProfilo;
    public ControlVisualizzaProfilo() {
        datiProfilo = EntityUtente.getDatiProfilo();
        Util.setScene("/com/example/progettocompleto/FXML/GestioneProfilo/SchermataVisualizzaProfilo.fxml", stage, c->new SchermataVisualizzaProfilo(this, (ArrayList<Object>) datiProfilo));
    }

    public void clickModifica(){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneProfilo/SchermataModificaProfilo.fxml", stage, c-> new SchermataModificaProfilo(this));
    }
    public void clickModificaPassword(){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneProfilo/SchermataModificaPassword.fxml", stage, c-> new SchermataModificaPassword(this));
    }
    public boolean verifyPassword(String nuovapass, String confpass) {
        boolean isValid = nuovapass.length() <= 30;
        return isValid && nuovapass.equals(confpass);
    }

    public void clickConferma( String vecpass, String nuovapass, String confpass, int matricola) throws IOException, SQLException {

        if (verifyPassword(nuovapass, confpass)) {

            if (Daemon.verifyPassword2(vecpass, matricola)) {
                Daemon.updatePassword(nuovapass, matricola);
                Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c->new PopupInformazione("Password modificata con successo!",stagePopup));
                stagePopup.showAndWait();
                SchermataVisualizzaProfilo.show(Daemon.getDatiProfilo(EntityUtente.getMatricola()));
            } else {
                Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stagePopup,c->new PopupErrore("Vecchia passwrod non corretta",stagePopup));
                stagePopup.showAndWait();
                SchermataModificaPassword.show();
            }
        } else {
            Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupErrore.fxml",stagePopup,c->new PopupErrore("Le due password non corrispondono",stagePopup));
            stagePopup.showAndWait();
            SchermataModificaPassword.show();
        }

    }
    public boolean verifyInsert(String recapito, String iban){
        boolean isValid= recapito.length()<=12;
        boolean isVali= iban.length()>26 ;
        return isValid && isVali;
    }
    public void clickSalva(List<Object> datiModificati, InputStream path) throws IOException {
        String recapito= datiModificati.get(0).toString();
        String iban= datiModificati.get(1).toString();
        String indi= datiModificati.get(2).toString();
        String mail= datiModificati.get(3).toString();
        if(verifyInsert(recapito, iban)){
            int matricola = EntityUtente.getMatricola();
            if(Daemon.updateDatiProfilo(Long.parseLong(recapito),indi, iban,mail, path, matricola)){

                ArrayList<Object> datiProfilo;
                datiProfilo=(ArrayList<Object>) EntityUtente.getDatiProfilo();
                Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupInformazione.fxml",stagePopup,c-> new PopupInformazione("Dati modificati Correttamente",stagePopup));
                stagePopup.showAndWait();
                SchermataVisualizzaProfilo.show(Daemon.getDatiProfilo(matricola),Daemon.getFotoProfilo(matricola));

            }else {
                ArrayList<Object> datiProfilo;
                datiProfilo=(ArrayList<Object>)  EntityUtente.getDatiProfilo();
                Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupErrore.fxml",stagePopup,c-> new PopupErrore("Impossibile cambiare i dati ali",stagePopup));
                stagePopup.showAndWait();
                Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/FXML/SchermataVisualizzaProfilo.fxml", stage, c-> new SchermataVisualizzaProfilo(this,datiProfilo ));

            }
        }else{
            ArrayList<Object> datiProfilo;
            datiProfilo=(ArrayList<Object>)  EntityUtente.getDatiProfilo();
            Util.setScenePopup("/com/example/progettocompleto/View/Popup/PopupErrore.fxml",stagePopup,c-> new PopupErrore("Impossibile cambiare i dati",stagePopup));
            stagePopup.showAndWait();
            Util.setSpecificScene("/com/example/progettocompleto/FXML/GestioneAutenticazione/FXML/SchermataVisualizzaProfilo.fxml", stage, c-> new SchermataVisualizzaProfilo(this,datiProfilo ));

        }

    }

    public void clickIndietro(String fxml){
        Util.ritorno(fxml);
    }

}


