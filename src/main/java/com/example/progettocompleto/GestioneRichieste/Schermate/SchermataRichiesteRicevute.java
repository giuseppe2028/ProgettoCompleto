package com.example.progettocompleto.GestioneRichieste.Schermate;


import com.example.progettocompleto.Contenitori.Richiesta;
import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.FileDiSistema.Util;
import com.example.progettocompleto.GestioneRichieste.Control.ControlRichiesteRicevute;
import com.example.progettocompleto.Start;
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

public class SchermataRichiesteRicevute {
    private ControlRichiesteRicevute controlRichiesteRicevute;
    ObservableList<Richiesta> richiesteList;
    @FXML
    private TableView<Richiesta> richiesteTableView;
    @FXML
    private TableColumn<Richiesta, Integer> idCol;
    @FXML
    private TableColumn<Richiesta, String> turnoOCol;
    @FXML
    private TableColumn<Richiesta, String> turnoDCol;
    @FXML
    private TableColumn<Richiesta, LocalDate> dataTOCol;
    @FXML
    private TableColumn<Richiesta, LocalDate> dataTDCol;

    @FXML
    private TableColumn<Richiesta, String> catCol;
    @FXML
    private TableColumn editCol;
    Richiesta richiesta;

    public SchermataRichiesteRicevute(ControlRichiesteRicevute controlRichiesteRicevute){

        this.controlRichiesteRicevute=controlRichiesteRicevute;

    }
    public void initialize() {
        try {
            loadDate();
        } catch (SQLException e) {
            System.out.println("err");
        }

    }
    public void loadDate() throws SQLException {
        richiesteList = FXCollections.observableArrayList();
        List<Richiesta> richieste= Daemon.getRichiesteRicevute(EntityUtente.getMatricola());
        for (int i = 0; i<richieste.size();i++) {
            richiesteList.add(new Richiesta(richieste.get(i).getCategoria(), richieste.get(i).getId(),richieste.get(i).getTipo_turno_origine(),
                    richieste.get(i).getTipo_turno_destinazione(), richieste.get(i).getData_turno_origine(),richieste.get(i).getData_turno_destinazione()));

        }
        catCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        turnoOCol.setCellValueFactory(new PropertyValueFactory<>("tipo_turno_origine"));
        turnoDCol.setCellValueFactory(new PropertyValueFactory<>("tipo_turno_destinazione"));
        dataTOCol.setCellValueFactory(new PropertyValueFactory<>("data_turno_origine"));
        dataTDCol.setCellValueFactory(new PropertyValueFactory<>("data_turno_destinazione"));

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
                        final Button rifiutaButton= new Button("rifiuta");
                        accettaButton.setBackground(Background.fill(Color.AZURE));
                        rifiutaButton.setBackground(Background.fill(Color.AZURE));
                        accettaButton.setOnAction((ActionEvent event) -> {

                            richiesta =  richiesteTableView.getSelectionModel().getSelectedItem();
                            try {
                                controlRichiesteRicevute.clickAccetta(richiesta.getId(),richiesta.getRef_impiegato(),richiesta.getMatricola_destinazione());

                                updateTable();

                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }


                        });
                        rifiutaButton.setOnAction((ActionEvent event) -> {

                            richiesta = richiesteTableView.getSelectionModel().getSelectedItem();
                            try {
                                controlRichiesteRicevute.clickRifiuta(richiesta.getId());
                                 updateTable();

                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }


                    });


                        HBox managebtn = new HBox(accettaButton, rifiutaButton);
                        managebtn.setStyle("-fx-alignment: center");
                        HBox.setMargin(rifiutaButton, new Insets(2, 2, 0, 3));
                        HBox.setMargin(accettaButton, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);
                        setText(null);
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
    public static void show(ControlRichiesteRicevute controlRichiesteRicevute){
        Util.setScene("/com/example/progettocompleto/GestioneRichieste/FXML/SchermataGestioneRichieste.fxml", Start.mainStage,c->new SchermataRichiesteRicevute(controlRichiesteRicevute));
    }
}
