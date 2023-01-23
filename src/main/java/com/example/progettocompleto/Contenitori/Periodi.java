package com.example.progettocompleto.Contenitori;

import javafx.fxml.FXML;

import java.time.LocalDate;

public class Periodi {
    private  int id;
    private String categoria;
    private LocalDate dataInizio, dataFine;
    public Periodi(LocalDate dataInizio,LocalDate dataFine,String categoria, int id){
        this.dataFine = dataFine;
        this.dataInizio = dataInizio;
        this.categoria = categoria;
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
@Override
    public String toString(){
        return categoria;
}
}
