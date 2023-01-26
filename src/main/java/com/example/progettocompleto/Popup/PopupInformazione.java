package com.example.progettocompleto.Popup;

import com.example.progettocompleto.FileDiSistema.ControlInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupInformazione {
    Stage stage;

    private String message;

    @FXML
    Label messaggioInformazione;

    @FXML
    Label informazione;

   private  ControlInterface controlInterface;

    @FXML
    public void initialize(){
        informazione.setText("Informazione");
        messaggioInformazione.setText(this.message);
    }







    public PopupInformazione(String message,ControlInterface controlInterface,Stage stage){
        this.stage = stage;
        this.controlInterface = controlInterface;
        this.message = message;
        //messaggioInformazione.setText(this.message);

    }

    @FXML
    public void clickOK(ActionEvent event) throws IOException {
        controlInterface.clickOK();
        stage.close();
    }
}

