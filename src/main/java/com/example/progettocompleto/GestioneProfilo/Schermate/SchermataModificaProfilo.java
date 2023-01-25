package com.example.progettocompleto.GestioneProfilo.Schermate;



import com.example.progettocompleto.FileDiSistema.Daemon;
import com.example.progettocompleto.FileDiSistema.EntityUtente;
import com.example.progettocompleto.GestioneProfilo.Control.ControlVisualizzaProfilo;
import com.example.progettocompleto.Start;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static jdk.jfr.consumer.EventStream.openFile;


public class SchermataModificaProfilo  {
    @FXML
    Button indietro;
    @FXML
    TextField recapitoField;
    @FXML
    TextField indirizzoField;
    @FXML
    TextField ibanField;
    @FXML
    TextField mailField;
    @FXML
    Button carica;
    @FXML
    ImageView immagineView;

    private ControlVisualizzaProfilo controlVisualizzaProfilo;


    public SchermataModificaProfilo(ControlVisualizzaProfilo controlVisualizzaProfilo) {
        this.controlVisualizzaProfilo = controlVisualizzaProfilo;

    }

    public void initialize() throws IOException {
        Image im;
        InputStream is = Daemon.getFotoProfilo(EntityUtente.getMatricola());
        im = new Image(is);
        immagineView.setImage(im);
        is.close();
    }


    public void clickSalva(ActionEvent e) throws IOException {
        Double recapito = Double.valueOf((recapitoField.getText()));
        String iban = ibanField.getText();
        String indi = indirizzoField.getText();
        String mail = mailField.getText();
        Image foto = immagineView.getImage();

        String path = foto.getUrl().substring(5);
        FileInputStream inputStream = new FileInputStream(path);

        List<Object> datiModificati = new ArrayList<>();
        datiModificati.add(recapito);
        datiModificati.add(iban);
        datiModificati.add(indi);
        datiModificati.add(mail);

        controlVisualizzaProfilo.clickSalva(datiModificati, inputStream);
    }

    @FXML
    public void clickCarica(ActionEvent event) {
        carica.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        Stage stage = Start.mainStage;
                        FileChooser fileChooser = new FileChooser();
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            try {
                                openFile(file.toPath());
                                Image image = new Image(file.toURI().toString());
                                immagineView.setImage(image);

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
    }
        public void clickIndietro(ActionEvent e){
            controlVisualizzaProfilo.clickIndietro("com/example/progettocompleto/GestioneProfilo/FXML/SchermataVisualizzaProfilo.fxml");
        }

    }





