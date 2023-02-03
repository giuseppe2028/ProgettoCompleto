package com.example.progettocompleto.View.SchermateGestioneImpiegati;





import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlGestioneImpiegati;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class SchermataDisattivaImpiegato {

    @FXML
    private TextField matricola;


    ControlGestioneImpiegati controlGestioneImpiegati;



    public SchermataDisattivaImpiegato(ControlGestioneImpiegati controlGestioneImpiegati){
        this.controlGestioneImpiegati=controlGestioneImpiegati;

    }

    public void clickDisattiva(ActionEvent e){
        int matricolaImpiegato = Integer.parseInt(matricola.getText());
        controlGestioneImpiegati.clickDisattiva( matricolaImpiegato);
    }


    public static void show(){
        Util.setScene("/com/example/progettocompleto/GestioneImpiegati/FXML/SchermataDisattivaImpiegato.fxml", Start.mainStage, c->new SchermataDisattivaImpiegato(new ControlGestioneImpiegati()));
    }



}
