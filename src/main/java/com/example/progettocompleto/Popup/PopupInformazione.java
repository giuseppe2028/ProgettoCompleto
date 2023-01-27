package com.example.progettocompleto.Popup;

import com.example.progettocompleto.FileDiSistema.ControlInterfaceErrore;
import com.example.progettocompleto.FileDiSistema.ControlInterfaceInformazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupInformazione {
    Stage stage;

    private String message;

    @FXML
    Label messaggioInformazione;

    @FXML
    Label informazione;

   private ControlInterfaceInformazione controlInterface;

    @FXML
    public void initialize(){
        informazione.setText("Informazione");
        messaggioInformazione.setText(this.message);
    }







    public PopupInformazione(String message,Stage stage){
        this.stage = stage;
        this.message = message;
        //messaggioInformazione.setText(this.message);

    }

    @FXML
    public void clickOK(ActionEvent event) throws IOException {
        stage.close();
    }
}

