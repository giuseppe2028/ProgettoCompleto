package com.example.progettocompleto.GestioneImpiegati.Schermate;


import com.example.progettocompleto.Contenitori.Impiegati;
import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneImpiegati.Control.ControlGestioneImpiegati;
import com.example.progettocompleto.GestioneImpiegati.Control.ControlRichiesteSciopero;
import com.example.progettocompleto.Start;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SchermataGestioneImpiegati implements Initializable {
    ControlGestioneImpiegati controlGestioneImpiegati;
    Stage stage= Start.mainStage;
    private Impiegati impiegato;
    ObservableList<Impiegati> listaTabella;
    List<Impiegati> lista;
    public SchermataGestioneImpiegati(ControlGestioneImpiegati controlGestioneImpiegati){
        this.controlGestioneImpiegati=controlGestioneImpiegati;
    }
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){

        servizio.getItems().add("1");
        servizio.getItems().add("2");
        servizio.getItems().add("3");
        servizio.getItems().add("4");
        servizio.getItems().add("Tutti");
        servizio.setValue("Tutti");
        //aggiungo i ruoli alla schermata

       ruolo.getItems().add("Impiegato");
        ruolo.getItems().add("Amministrativo");
        ruolo.getItems().add("Tutti");
        ruolo.setValue("Tutti");
       ruolo.setOnAction((event)-> {

            if(ruolo.getValue().equals("Tutti")){

                visualizzaTabellaFiltrata(0,null);
            }
            else{
                visualizzaTabellaFiltrata(0,ruolo.getValue());
            }
        });
        //gestisco il cambiamento
        servizio.setOnAction((event)-> {
           if(servizio.getValue().equals("Tutti")){
               visualizzaTabellaFiltrata(0,null);
           }
           else{
               visualizzaTabellaFiltrata(Integer.parseInt(servizio.getValue()),null);
           }

        });

        //lista = Daemon.getImpiegati(servizio.getValue(),ruolo.getValue());
        listaTabella = FXCollections.observableArrayList();
        //lista = Daemon.getImpiegati("Impiegato");
        lista = Daemon.getImpiegati();
        for(int i = 0; i<lista.size();i++){
            listaTabella.add(new Impiegati(lista.get(i).getMatricola(), lista.get(i).getNome(),lista.get(i).getCognome(),lista.get(i).getServizio(),lista.get(i).getRuolo()));

        }
        System.out.println(lista.size());
        matricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        servizioColonna.setCellValueFactory(new PropertyValueFactory<>("servizio"));
        ruoloColonna.setCellValueFactory(new PropertyValueFactory<>("ruolo"));


        tabella.setItems(listaTabella);
        Callback<TableColumn<Impiegati, String>, TableCell<Impiegati, String>> cellFactory = /*(TableColumn<Richieste, String>*/ (param) -> {

            final TableCell<Impiegati, String> cell = new TableCell<>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    //se la cella è vuota AND almeno un item è null non setta i bottoni
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //todo cambiare i colori degli hyperlink
                        tabella.setBackground(Background.fill(Color.WHITE));
                        final Hyperlink visualizzaDati = new Hyperlink("visualizza Dati");
                        final Hyperlink visualizzaStipendio = new Hyperlink("visualizza Stipendio");
                        final Hyperlink timbra = new Hyperlink("timbra");
                        visualizzaDati.setOnAction((ActionEvent event) -> {
                            impiegato = tabella.getSelectionModel().getSelectedItem();
                            controlGestioneImpiegati.clickVisualizzaDati(impiegato, impiegato.getServizio());

                        });
                        visualizzaStipendio.setOnAction((ActionEvent event)->{
                            impiegato = tabella.getSelectionModel().getSelectedItem();
                            controlGestioneImpiegati.clickVisualizzaStipendio();


                        });
                        timbra.setOnAction((ActionEvent event)->{
                            impiegato= tabella.getSelectionModel().getSelectedItem();
                            controlGestioneImpiegati.clickTimbraturaImpiegato();

                        });

                        HBox managebtn = new HBox(visualizzaDati,visualizzaStipendio,timbra);
                        managebtn.setStyle("-fx-alignment: center");
                        HBox.setMargin(visualizzaDati, new Insets(2, 2, 0, 3));
                        HBox.setMargin(visualizzaStipendio, new Insets(4, 4, 0, 6));

                        setGraphic(managebtn);
                        setText(null);
                    }

                }


            };

            return cell;
        };
        visualizzaDati.setCellFactory(cellFactory);
        tabella.setItems(listaTabella);



    }
    @FXML
    private ChoiceBox<String> servizio;
    @FXML
    private ChoiceBox<String> ruolo;

    @FXML
    private Button bottoneAggiungiImpiegato;

    @FXML
    private Button bottoneDisattivaImpiegato;

    @FXML
    private Button bottoneIndietro;

    @FXML
    private Button bottoneVisualizza;

    @FXML
    private Button bottoneVisualizzaCalendario;
    @FXML
    private TableView<Impiegati> tabella;
    @FXML
    private TableColumn<Impiegati,Integer> matricola;
    @FXML
    private TableColumn<Impiegati,String> nome;
    @FXML
    private TableColumn<Impiegati,String> cognome;
    @FXML
    private TableColumn<Impiegati,Integer> servizioColonna;
    @FXML
    private TableColumn<Impiegati,String> ruoloColonna;
    @FXML
    private TableColumn visualizzaDati;



    @FXML
    void clickIndietro(ActionEvent event) {
        System.out.println("sesso con cavalli");
       // Util.setScene("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataPrincipaleDatore.fxml",stage);
        Util.ritorno("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataPrincipaleDatore.fxml");
    }
/*private List<Impiegati> visualizzaTabellaServizio(int filtro){
        List<Impiegati> listaFiltrata ;
    //    listaFiltrata = lista.stream().filter(s->)

}
*/


private void visualizzaTabellaRuolo(String filtro){
List<Impiegati> listaFiltrata = lista.stream().filter(s-> s.getRuolo().startsWith(filtro)).collect(Collectors.toList());
    listaTabella = FXCollections.observableArrayList();
    for(int i = 0; i<listaFiltrata.size();i++){
        listaTabella.add(new Impiegati(listaFiltrata.get(i).getMatricola(), listaFiltrata.get(i).getNome(),listaFiltrata.get(i).getCognome(),listaFiltrata.get(i).getServizio(),listaFiltrata.get(i).getRuolo()));

    }

    System.out.println(lista.size());
    matricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));
    nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
    servizioColonna.setCellValueFactory(new PropertyValueFactory<>("servizio"));
    ruoloColonna.setCellValueFactory(new PropertyValueFactory<>("ruolo"));


    tabella.setItems(listaTabella);
    Callback<TableColumn<Impiegati, String>, TableCell<Impiegati, String>> cellFactory = /*(TableColumn<Richieste, String>*/ (param) -> {

        final TableCell<Impiegati, String> cell = new TableCell<>() {

            @Override
            public void updateItem(String item, boolean empty) {
                //se la cella è vuota AND almeno un item è null non setta i bottoni
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    //todo cambiare i colori degli hyperlink
                    tabella.setBackground(Background.fill(Color.WHITE));
                    final Hyperlink visualizzaDati = new Hyperlink("visualizza Dati");
                    final Hyperlink visualizzaStipendio = new Hyperlink("visualizza Stipendio");
                    final Hyperlink timbra = new Hyperlink("timbra");
                    visualizzaDati.setOnAction((ActionEvent event) -> {
                        impiegato = tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickVisualizzaDati(impiegato, impiegato.getServizio());

                    });
                    visualizzaStipendio.setOnAction((ActionEvent event)->{
                        impiegato = tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickVisualizzaStipendio();


                    });
                    timbra.setOnAction((ActionEvent event)->{
                        impiegato= tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickTimbraturaImpiegato();

                    });

                    HBox managebtn = new HBox(visualizzaDati,visualizzaStipendio,timbra);
                    managebtn.setStyle("-fx-alignment: center");
                    HBox.setMargin(visualizzaDati, new Insets(2, 2, 0, 3));
                    HBox.setMargin(visualizzaStipendio, new Insets(4, 4, 0, 6));

                    setGraphic(managebtn);
                    setText(null);
                }

            }


        };

        return cell;
    };
    visualizzaDati.setCellFactory(cellFactory);
    tabella.setItems(listaTabella);




}
private void visualizzaTabellaFiltrata(int servizio, String ruolo){
    //List<Impiegati> listaFiltrata = controlGestioneImpiegati.filtra(ruolo,servizio,lista);
    lista = controlGestioneImpiegati.filtra(ruolo,servizio,lista);

            listaTabella = FXCollections.observableArrayList();
    for(int i = 0; i<lista.size();i++){
        listaTabella.add(new Impiegati(lista.get(i).getMatricola(), lista.get(i).getNome(),lista.get(i).getCognome(),lista.get(i).getServizio(),lista.get(i).getRuolo()));

    }

    System.out.println(lista.size());
    matricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));
    nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
    servizioColonna.setCellValueFactory(new PropertyValueFactory<>("servizio"));
    ruoloColonna.setCellValueFactory(new PropertyValueFactory<>("ruolo"));


    tabella.setItems(listaTabella);
    Callback<TableColumn<Impiegati, String>, TableCell<Impiegati, String>> cellFactory = /*(TableColumn<Richieste, String>*/ (param) -> {

        final TableCell<Impiegati, String> cell = new TableCell<>() {

            @Override
            public void updateItem(String item, boolean empty) {
                //se la cella è vuota AND almeno un item è null non setta i bottoni
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    //todo cambiare i colori degli hyperlink
                    tabella.setBackground(Background.fill(Color.WHITE));
                    final Hyperlink visualizzaDati = new Hyperlink("visualizza Dati");
                    final Hyperlink visualizzaStipendio = new Hyperlink("visualizza Stipendio");
                    final Hyperlink timbra = new Hyperlink("timbra");
                    visualizzaDati.setOnAction((ActionEvent event) -> {
                        impiegato = tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickVisualizzaDati(impiegato, impiegato.getServizio());

                    });
                    visualizzaStipendio.setOnAction((ActionEvent event)->{
                        impiegato = tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickVisualizzaStipendio();


                    });
                    timbra.setOnAction((ActionEvent event)->{
                        impiegato= tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickTimbraturaImpiegato();

                    });

                    HBox managebtn = new HBox(visualizzaDati,visualizzaStipendio,timbra);
                    managebtn.setStyle("-fx-alignment: center");
                    HBox.setMargin(visualizzaDati, new Insets(2, 2, 0, 3));
                    HBox.setMargin(visualizzaStipendio, new Insets(4, 4, 0, 6));

                    setGraphic(managebtn);
                    setText(null);
                }

            }


        };

        return cell;
    };
    visualizzaDati.setCellFactory(cellFactory);
    tabella.setItems(listaTabella);





}

public void clickRegistraImpiegato(ActionEvent e){
    controlGestioneImpiegati.clickRegistra();
}

public void clickDisattiva(ActionEvent e){
    controlGestioneImpiegati.clickDisattiva();
}
    public void clickRichiesteSciopero(ActionEvent e){
        ControlRichiesteSciopero controlRichiesteSciopero= new ControlRichiesteSciopero();
        controlRichiesteSciopero.clickRichiestaSciopero();
    }
}