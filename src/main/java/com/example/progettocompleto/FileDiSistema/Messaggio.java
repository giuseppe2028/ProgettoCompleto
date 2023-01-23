package com.example.progettocompleto.FileDiSistema;


import javafx.scene.control.Alert;

public class Messaggio {
    Alert messaggio = new Alert(Alert.AlertType.INFORMATION);

    public Messaggio(String message){
        messaggio.setContentText(message);
        messaggio.show();
    }

}
