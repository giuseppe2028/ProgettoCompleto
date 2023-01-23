package com.example.progettocompleto.Contenitori;

import javafx.scene.control.Button;

import java.time.LocalDate;

public class RigaGiorniProibiti {
    private LocalDate dataInizio,dataFine;
    private String categoria;
    private Button rimuovi;
    //nell'ODD si mette che a causa della conformazione di javaFX, abbiamo dovuto aggiungere nuove entity
    public RigaGiorniProibiti(LocalDate dataInizio,LocalDate dataFine,String categoria, Button rimuovi){
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.categoria = categoria;
        this.rimuovi = rimuovi;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Button getRimuovi() {
        return rimuovi;
    }

    public void setRimuovi(Button rimuovi) {
        this.rimuovi = rimuovi;
    }
}
