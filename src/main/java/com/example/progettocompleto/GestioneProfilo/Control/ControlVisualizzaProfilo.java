package com.example.progettocompleto.GestioneProfilo.Control;



import com.example.progettocompleto.FileDiSistema.*;
import com.example.progettocompleto.GestioneProfilo.Schermate.SchermataModificaPassword;
import com.example.progettocompleto.GestioneProfilo.Schermate.SchermataModificaProfilo;
import com.example.progettocompleto.GestioneProfilo.Schermate.SchermataVisualizzaProfilo;
import com.example.progettocompleto.Popup.PopupInformazione;
import com.example.progettocompleto.Start;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlVisualizzaProfilo implements ControlInterfaceInformazione {
    private Stage stagePopup = new Stage();
    private Stage stage = Start.mainStage;
    private List<Object> datiProfilo;
    public ControlVisualizzaProfilo() {
        datiProfilo = EntityUtente.getDatiProfilo();
        Util.setScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataVisualizzaProfilo.fxml", stage, c->new SchermataVisualizzaProfilo(this, (ArrayList<Object>) datiProfilo));
    }

    public void clickModifica(){
        Util.setScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataModificaProfilo.fxml", stage, c-> new SchermataModificaProfilo(this));
    }
    public void clickModificaPassword(){
        Util.setScene("/com/example/progettocompleto/GestioneProfilo/FXML/SchermataModificaPassword.fxml", stage, c-> new SchermataModificaPassword(this));
    }
    public boolean verifyPassword(String nuovapass, String confpass) {
        boolean isValid = nuovapass.length() <= 30;
        return isValid && nuovapass.equals(confpass);
    }

    public void clickConferma( String vecpass, String nuovapass, String confpass, int matricola) throws IOException, SQLException {

        if (verifyPassword(nuovapass, confpass)) {

            if (Daemon.verifyPassword2(vecpass, matricola)) {
                //todo Daemon.updatePassword(nuovapass, matricola);
                //Daemon.updatePassword(nuovapass, matricola);
                Util.setScenePopup("/com/example/progettocompleto/Popup/FXML/PopupInformazione.fxml",stagePopup,c->new PopupInformazione("Password modificata con successo!",this,stagePopup));
                stagePopup.show();
            } else {
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setTitle("Errore");
                a.setContentText("la vecchia password non corrisponde");
                a.showAndWait();
            }
        } else {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setTitle("Errore");
            a.setContentText("le password non sono valide");
            a.showAndWait();
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

            if(Daemon.updateDatiProfilo(Double.valueOf(recapito), iban, indi, mail, path, matricola)){
                Alert a= new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Dati cambiati con successo!");
                a.showAndWait();
                ArrayList<Object> datiProfilo;
                datiProfilo=(ArrayList<Object>) EntityUtente.getDatiProfilo();
                Util.setSpecificScene("/com/example/progettocompleto/GestioneAutenticazione/FXML/SchermataVisualizzaProfilo.fxml", stage, c-> new SchermataVisualizzaProfilo(this,datiProfilo ));
            }else {
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setTitle("Errore");
                a.setContentText("Impossibile cambiare i dati");
                a.showAndWait();
            }
        }else{
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setTitle("Errore");
            a.setContentText("l'iban o il recapito telefonico sono sbagliati");
            a.showAndWait();
        }

    }

    public void clickIndietro(String fxml){
        Util.ritorno(fxml);
    }


    @Override
    public void clickOKInformazione() {

    }
}


