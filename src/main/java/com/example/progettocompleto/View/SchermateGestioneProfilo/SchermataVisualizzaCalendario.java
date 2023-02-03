package com.example.progettocompleto.View.SchermateGestioneProfilo;

import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.EntityUtente;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class SchermataVisualizzaCalendario {
    private int meseScelto,annoScelto;
    private String[] mesi = {"Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago", "Set", "Ott", "Nov", "Dic"};
    private Date first;
    @FXML
    private GridPane gridPane;
    @FXML
    private ChoiceBox<String> mese;
    @FXML
    private ChoiceBox<Integer> anno;
    @FXML
    public void initialize(){

        int meseScelto = LocalDate.now().getMonthValue();
        int annoScelto = LocalDate.now().getYear();
        //setto i mesi e gli anni
        //faccio visualizzare prima il calendario
        visualizzaTabella(LocalDate.now().getMonthValue(),LocalDate.now().getYear());
        for(int i = 0; i<mesi.length; i++ ){
            mese.getItems().add(mesi[i]);
        }
        for(int i = 2023; i>1997; i--){
            anno.getItems().add(i);
        }
        //seleziono i valori di default
        mese.setValue(mesi[LocalDate.now().getMonthValue()-1]);
        anno.setValue(LocalDate.now().getYear());
        mese.setOnAction(event->{
            aggiornaVariabili(valoreMese(mese.getValue()),annoScelto);
            visualizzaTabella(valoreMese(mese.getValue()),annoScelto);
        });
    }
    private void aggiornaVariabili(int meseScelto,int annoScelto){
        this.annoScelto = annoScelto;
        this.meseScelto = meseScelto;
    }
    private void visualizzaTabella(int mese,int anno){
        System.out.println(gridPane.getChildren().size());
        int indice = 1;
        for(int i = 1; i<7;i++){
            for(int j=0; j<7; j++){
                Label label = new Label();
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.getChildren().add(label);
                AnchorPane.setLeftAnchor(label,170.0);
                AnchorPane.setTopAnchor(label,10.0);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, mese-1);
                calendar.set(Calendar.YEAR, anno);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                first = calendar.getTime();
                int daysInMonth = calendar.getActualMaximum(Calendar.DATE);
                int ottieniInizio = ottieniInizio(first);
                if(i==1&&j<ottieniInizio){
                    label.setText("");
                }else if(indice < LocalDate.of(anno,mese,indice).lengthOfMonth()){
                    label.setText(String.valueOf(indice));
                    indice++;
                    System.out.println("La data è:"+LocalDate.of(anno,mese,indice));
                    System.out.println(Daemon.isInAstensione(LocalDate.of(anno,mese,indice), EntityUtente.getMatricola()));
                    //controllo se qul cristiano ha un tuno o un periodo di astensione
                    if(Daemon.isInAstensione(LocalDate.of(anno,mese,indice), EntityUtente.getMatricola()) !=null){
                        Label label1 = new Label();
                        label1.setText(Daemon.isInAstensione(LocalDate.of(anno,mese,indice), EntityUtente.getMatricola()));
                        //imposto la grandezza della
                        System.out.println("ciao"+gridPane.getColumnConstraints().get(j).getPrefWidth());
                        anchorPane.setMaxWidth(gridPane.getColumnConstraints().get(j).getPrefWidth());
                        anchorPane.setMinWidth(gridPane.getColumnConstraints().get(j).getPrefWidth());
                        label1.setMaxWidth(gridPane.getColumnConstraints().get(j).getPrefWidth()+105);
                        label1.setMinWidth(gridPane.getColumnConstraints().get(j).getPrefWidth()+105);
                        //coloro lo sfondo della label
                        label1.setStyle("-fx-background-color: rgba(255,0,0,0.2);-fx-alignment: center;-fx-text-fill: black");
                        AnchorPane.setLeftAnchor(label1,0.0);
                        AnchorPane.setTopAnchor(label1,40.0);
                        anchorPane.getChildren().add(label1);

                    }
                }
                gridPane.add(anchorPane,j,i);
            }
        }
    }
    private int ottieniInizio(Date first){
        //mi faccio dare la data e prendo solamento il giorno a parola
        String caso  = first.toString().substring(0,3);
        switch (caso){
            case "Mon":
                return 0;
            case "Tue":
                return 1;
            case "Wed":
                return 2;
            case "Thu":
                return 3;
            case "Fri":
                return 4;
            case "Sat":
                return 5;
            case "Sun":
                return 6;
        }
        return 0;
    }
    private int valoreMese(String mese) {
        switch (mese) {
            case "Gen":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "Mag":
                return 5;
            case "Giu":
                return 6;
            case "Lug":
                return 7;
            case "Ago":
                return 8;
            case "Set":
                return 9;
            case "Ott":
                return 10;
            case "Nov":
                return 11;
            case "Dic":
                return 12;

        }
        return 0;
    }
}
