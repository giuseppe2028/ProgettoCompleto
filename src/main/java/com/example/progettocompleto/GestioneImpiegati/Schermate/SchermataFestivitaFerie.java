package com.example.progettocompleto.GestioneImpiegati.Schermate;


import com.example.progettocompleto.Contenitori.Periodi;
import com.example.progettocompleto.Contenitori.RigaGiorniProibiti;
import com.example.progettocompleto.GestioneImpiegati.Control.ControlFestivitaFerie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class SchermataFestivitaFerie {
    List<Periodi> periodi;
    private ControlFestivitaFerie controlFestivitaFerie;
    public SchermataFestivitaFerie(ControlFestivitaFerie controlFestivitaFerie, List<Periodi> periodi){

        this.periodi = periodi;
        this.controlFestivitaFerie = controlFestivitaFerie;
        visualizzaTabella ();
    }
    @FXML
    public void initialize(){

    }
    @FXML
    private Button bottoneIndietro;

    @FXML
    private Button bottoneInvia;


    @FXML
    private TableColumn<Periodi,String> dataInizio;
    @FXML
    private TableColumn<Periodi,String> dataFine;
    @FXML
    private TableColumn<Periodi,String> categoria;
    @FXML
    private TableColumn<Periodi,Button> rimuovi;
    @FXML
    private TableView<RigaGiorniProibiti> tabella;
    Button rimuoviBottone;
    private ObservableList<RigaGiorniProibiti> giorniProibitiTabella;

    private void visualizzaTabella(){
        giorniProibitiTabella = FXCollections.observableArrayList();
        for(int i = 0; i<periodi.size(); i++){
            System.out.println(periodi.get(i).getDataInizio());
            giorniProibitiTabella.add(new RigaGiorniProibiti(periodi.get(i).getDataInizio(),periodi.get(i).getDataFine(),periodi.get(i).getCategoria(),rimuoviBottone));
        }
        System.out.println(giorniProibitiTabella.size());
        dataInizio.setCellValueFactory(new PropertyValueFactory<>("dataInizio"));
        dataFine.setCellValueFactory(new PropertyValueFactory<>("dataFine"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        rimuovi.setCellValueFactory(new PropertyValueFactory<>("rimuovi"));
        rimuovi.setCellFactory(
                col->{
                    TableCell<Periodi, Button> cell = new TableCell<Periodi, Button>() {
                        @Override
                        protected void updateItem(Button item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(item);
                            }
                        }
                    };
                    return cell;
                });
        tabella.setItems(giorniProibitiTabella);

    }
    @FXML
    void clickIndietro(ActionEvent event) {

    }

    @FXML
    void clickInvia(ActionEvent event) {

    }

}
