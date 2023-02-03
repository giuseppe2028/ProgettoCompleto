package com.example.progettocompleto.View.SchermateGestioneImpiegati;


import com.example.progettocompleto.Contenitori.Periodi;
import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.Util;
import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlFestivitaFerie;
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
import javafx.util.Callback;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SchermataFestivitaFerie {
    @FXML
    private DatePicker dataIn;
    @FXML
    private DatePicker dataFi;
    @FXML
    private ChoiceBox<String> categoriaChoice;
    private String[] categorie = {"festività", "ferie"};
    private List<Periodi> periodi;
    private ControlFestivitaFerie controlFestivitaFerie;
    @FXML
    private TableColumn<Periodi, String> dataInizio;
    @FXML
    private TableColumn<Periodi, String> dataFine;
    @FXML
    private TableColumn<Periodi, String> categoria;
    @FXML
    private TableColumn rimuovi;
    @FXML
    private TableView<Periodi> tabella;
    private Periodi periodo;

    private ObservableList<Periodi> giorniProibitiTabella;

    public SchermataFestivitaFerie(ControlFestivitaFerie controlFestivitaFerie, List<Periodi> periodi) {

        this.periodi = periodi;
        this.controlFestivitaFerie = controlFestivitaFerie;


    }

    @FXML
    public void initialize() {
        visualizzaTabella();

        dataIn.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate dateC, boolean empty) {
                super.updateItem(dateC, empty);
                if ((dateC.getDayOfWeek() == DayOfWeek.SUNDAY) || dateC.isBefore(LocalDate.now())) {
                    setDisable(true);

                }
            }

        });
        dataIn.setOnAction(e -> {
            LocalDate selectedDate = dataIn.getValue();
            dataFi.setValue(selectedDate);
            dataFi.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate dateC, boolean empty) {
                    super.updateItem(dateC, empty);

                    if ((dateC.getDayOfWeek() == DayOfWeek.SUNDAY) || dateC.isBefore(LocalDate.now())) {
                        setDisable(true);

                    }

                }
            });
        });

        categoriaChoice.getItems().addAll(categorie);

    }


    private void visualizzaTabella() {
        giorniProibitiTabella = FXCollections.observableArrayList();
        for (int i = 0; i < periodi.size(); i++) {

            giorniProibitiTabella.add(new Periodi(periodi.get(i).getDataInizio(), periodi.get(i).getDataFine(), periodi.get(i).getCategoria(), periodi.get(i).getId()));
        }
        dataInizio.setCellValueFactory(new PropertyValueFactory<>("dataInizio"));
        dataFine.setCellValueFactory(new PropertyValueFactory<>("dataFine"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        Callback<TableColumn<Periodi, String>, TableCell<Periodi, String>> cellFactory = /*(TableColumn<Periodi, String>*/ (param) -> {


            final TableCell<Periodi, String> cell = new TableCell<>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    //se la cella è vuota non setta i bottoni
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        tabella.setBackground(Background.fill(Color.WHITE));
                        final Button rimuoviButton = new Button("rimuovi");

                        rimuoviButton.setBackground(Background.fill(Color.AZURE));

                        rimuoviButton.setOnAction((ActionEvent event) -> {

                            periodo = tabella.getSelectionModel().getSelectedItem();
                            try {

                                controlFestivitaFerie.rimuoviPeriodo(periodo.getId());
                                updateTable();


                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }


                        });

                        HBox managebtn = new HBox(rimuoviButton);
                        managebtn.setStyle("-fx-alignment: center");
                        HBox.setMargin(rimuoviButton, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);
                        setText(null);
                    }

                }


            };

            return cell;
        };
        rimuovi.setCellFactory(cellFactory);

        tabella.setItems(giorniProibitiTabella);


    }




    @FXML
    void clickInvia(ActionEvent event) throws SQLException {
        LocalDate dataInizio = dataIn.getValue();
        LocalDate dataFine = dataFi.getValue();
        String categoria = categoriaChoice.getValue();
        controlFestivitaFerie.clickInvia(dataInizio, dataFine, categoria);
    }

    public void updateTable() throws SQLException {
        giorniProibitiTabella.clear();
        visualizzaTabella();
        tabella.setItems(giorniProibitiTabella);
        tabella.refresh();
    }

    public void clickIndietro(ActionEvent e) {

            controlFestivitaFerie.clickIndietro("/com/example/progettocompleto/GestioneImpiegati/FXML/SchermataPrincipaleDatore.fxml");



    }
    public static void show(List<Periodi> listaPeriodi){
        Util.setScene("/com/example/progettocompleto/FXML/GestioneImpiegati/SchermataFesitivitaFerie.fxml", Start.mainStage, c->new SchermataFestivitaFerie(new ControlFestivitaFerie(),listaPeriodi));
    }
}