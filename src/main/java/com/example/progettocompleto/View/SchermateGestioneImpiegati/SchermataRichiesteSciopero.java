package com.example.progettocompleto.View.SchermateGestioneImpiegati;


import com.example.progettocompleto.Contenitori.Richiesta;
import com.example.progettocompleto.Model.Daemon;
import com.example.progettocompleto.Model.EntityUtente;
import com.example.progettocompleto.Controller.ControlGestioneImpiegati.ControlRichiesteSciopero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SchermataRichiesteSciopero {
    ControlRichiesteSciopero controlRichiesteSciopero;
    public SchermataRichiesteSciopero(ControlRichiesteSciopero controlRichiesteSciopero) {
        this.controlRichiesteSciopero = controlRichiesteSciopero;
    }
    ObservableList<Richiesta> richiesteList;
    @FXML
    private TableView<Richiesta> richiesteTableView;
    @FXML
    private TableColumn<Richiesta, Integer> matrCol;
    @FXML
    private TableColumn<Richiesta, LocalDate> dataiCol;
    @FXML
    private TableColumn<Richiesta, LocalDate> datafCol;

    @FXML
    private TableColumn<Richiesta, String> statoCol;
    @FXML
    private TableColumn<Richiesta, String> svolgCol;
    @FXML
    private TableColumn<Richiesta, String> motivCol;
    @FXML
    private TableColumn editCol;
    Richiesta richiesta;
    public void initialize() {
        try {
            loadDate();
        } catch (SQLException e) {
            System.out.println("err");
        }

    }
    public void loadDate() throws SQLException {
        richiesteList = FXCollections.observableArrayList();
        List<Richiesta> richieste= Daemon.getRichiesteSciopero(EntityUtente.getMatricola());
        for (int i = 0; i<richieste.size();i++) {
            richiesteList.add(new Richiesta(richieste.get(i).getRef_impiegato(), richieste.get(i).getData_inizio(),richieste.get(i).getData_fine(),
                    richieste.get(i).getStato(), richieste.get(i).getSvolgimento(),richieste.get(i).getMotivazione(), richieste.get(i).getId()));

        }
        matrCol.setCellValueFactory(new PropertyValueFactory<>("ref_impiegato"));
        dataiCol.setCellValueFactory(new PropertyValueFactory<>("data_inizio"));
        datafCol.setCellValueFactory(new PropertyValueFactory<>("data_fine"));
        statoCol.setCellValueFactory(new PropertyValueFactory<>("stato"));
        svolgCol.setCellValueFactory(new PropertyValueFactory<>("svolgimento"));
        motivCol.setCellValueFactory(new PropertyValueFactory<>("motivazione"));

        richiesteTableView.setItems(richiesteList);

        Callback<TableColumn<Richiesta, String>, TableCell<Richiesta, String>> cellFactory = /*(TableColumn<Richieste, String>*/ (param) -> {

            final TableCell<Richiesta, String> cell = new TableCell<>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    //se la cella è vuota AND almeno un item è null non setta i bottoni
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        richiesteTableView.setBackground(Background.fill(Color.WHITE));
                        final Button accettaButton = new Button("accetta");
                        final Button rifiutaButton = new Button("rifiuta");
                        accettaButton.setBackground(Background.fill(Color.AZURE));
                        accettaButton.setFocusTraversable(false);
                        rifiutaButton.setFocusTraversable(false);
                        rifiutaButton.setBackground(Background.fill(Color.AZURE));
                        accettaButton.setOnAction((ActionEvent event) -> {

                            richiesta = richiesteTableView.getSelectionModel().getSelectedItem();
                            try {
                                Daemon.accettaSciopero(richiesta.getId());
                                // TODO: 21/01/23  pop up
                                updateTable();

                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }


                        });
                        rifiutaButton.setOnAction((ActionEvent event) -> {

                            richiesta = richiesteTableView.getSelectionModel().getSelectedItem();
                            try {
                                Daemon.rifiutaSciopero(richiesta.getId());
                                // TODO: 21/01/23  pop up
                                updateTable();

                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }


                        });
                        Richiesta richiesta = getTableView().getItems().get(getIndex());
                        if (richiesta.getStato().equals("in sospeso")) {


                            HBox managebtn = new HBox(accettaButton, rifiutaButton);
                            managebtn.setStyle("-fx-alignment: center");
                            HBox.setMargin(rifiutaButton, new Insets(2, 2, 0, 3));
                            HBox.setMargin(accettaButton, new Insets(2, 2, 0, 3));
                            setGraphic(managebtn);
                            setText(null);
                        }

                    }
                }

            };

            return cell;
        };
        editCol.setCellFactory(cellFactory);
        richiesteTableView.setItems(richiesteList);


    }


    public void updateTable() throws SQLException {
        richiesteList.clear();
        loadDate();
        richiesteTableView.setItems(richiesteList);
        richiesteTableView.refresh();
    }
    public void clickIndietro(ActionEvent e){
        controlRichiesteSciopero.clickIndietro();
    }
}


