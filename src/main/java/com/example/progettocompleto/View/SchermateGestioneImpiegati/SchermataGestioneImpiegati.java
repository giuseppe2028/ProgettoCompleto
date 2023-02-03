package com.example.progettocompleto.View.SchermateGestioneImpiegati;


import com.example.progettocompleto.Contenitori.Impiegati;
import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlGestioneImpiegati;
import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlRichiesteSciopero;
import com.example.progettocompleto.Start;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SchermataGestioneImpiegati  {
   private ControlGestioneImpiegati controlGestioneImpiegati;
    private Impiegati impiegato;
    private ObservableList<Impiegati> listaTabella;
    private List<Impiegati> lista;
    @FXML
    private ChoiceBox<String> servizio;
    @FXML
    private ChoiceBox<String> ruolo;
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


    public SchermataGestioneImpiegati(ControlGestioneImpiegati controlGestioneImpiegati,List<Impiegati> lista){
        this.controlGestioneImpiegati=controlGestioneImpiegati;
        this.lista = lista;
    }

    @FXML
    public void initialize(){

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
//todo ORROREEEE!!!!
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
                        visualizzaDati.setTextFill(Color.BLACK);
                        visualizzaStipendio.setTextFill(Color.BLACK);
                        timbra.setTextFill(Color.BLACK);
                        visualizzaDati.setOnAction((ActionEvent event) -> {
                            impiegato = tabella.getSelectionModel().getSelectedItem();
                            controlGestioneImpiegati.clickVisualizzaDati( impiegato.getMatricola());

                        });
                        visualizzaStipendio.setOnAction((ActionEvent event)->{
                            impiegato = tabella.getSelectionModel().getSelectedItem();
                            controlGestioneImpiegati.clickVisualizzaStipendio(impiegato.getMatricola(),convertMese(LocalDate.now().getMonthValue()),LocalDate.now().getYear());


                        });
                        timbra.setOnAction((ActionEvent event)->{
                            impiegato= tabella.getSelectionModel().getSelectedItem();
                            controlGestioneImpiegati.clickTimbraturaImpiegato(impiegato.getMatricola());

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
    void clickIndietro(ActionEvent event) {
       // Util.setScene("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataPrincipaleDatore.fxml",stage);
        Util.ritorno("/com/example/GestioneRemoto/GestioneAutenticazione/FXML/SchermataPrincipaleDatore.fxml");
    }
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
                        controlGestioneImpiegati.clickVisualizzaDati(impiegato.getMatricola());

                    });
                    visualizzaStipendio.setOnAction((ActionEvent event)->{
                        impiegato = tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickVisualizzaStipendio(impiegato.getMatricola(),convertMese(LocalDate.now().getMonthValue()),LocalDate.now().getYear());


                    });
                    timbra.setOnAction((ActionEvent event)->{
                        impiegato= tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickTimbraturaImpiegato(impiegato.getMatricola());

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
                        controlGestioneImpiegati.clickVisualizzaDati( impiegato.getMatricola());

                    });
                    visualizzaStipendio.setOnAction((ActionEvent event)->{
                        impiegato = tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickVisualizzaStipendio(impiegato.getMatricola(),convertMese(LocalDate.now().getMonthValue()),LocalDate.now().getYear());


                    });
                    timbra.setOnAction((ActionEvent event)->{
                        impiegato= tabella.getSelectionModel().getSelectedItem();
                        controlGestioneImpiegati.clickTimbraturaImpiegato(impiegato.getMatricola());

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
    public void clickCalendario(ActionEvent e){
        controlGestioneImpiegati.clickCalendario();
    }
    private String convertMese(int mese){
        switch (mese){
            case 1:
                return "Gennaio";
            case 2:
                return "Febbraio";
            case 3:
                return "Marzo";
            case 4:
                return "Aprile";
            case 5:
                return "Maggio";
            case 6:
                return "Giugno";
            case 7:
                return "Luglio";
            case 8:
                return "Agosto";
            case 9:
                return "Settembre";
            case 10:
                return "Ottobre";
            case 11:
                return "Novembre";
            case 12:
                return "Dicembre";

        }
        return null;
    }
    public static void show(ControlGestioneImpiegati controlGestioneImpiegati, List<Impiegati> lista){
        Util.setScene("/com/example/progettocompleto/GestioneImpiegati/FXML/SchermataGestioneImpiegati.fxml", Start.mainStage,c->new SchermataGestioneImpiegati(new ControlGestioneImpiegati(),lista));
    }
}
