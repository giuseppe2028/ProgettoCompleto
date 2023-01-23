package com.example.progettocompleto.FileDiSistema;

public class datiGenerati {
    private int matricola;
    private String mail;
    private int giorniFerie;
    private double orePermesso,oreStraordinario;


    public datiGenerati(int matricola, String mail, int giorniFerie, double orePermesso, double oreStraordinario) {
        this.matricola = matricola;
        this.mail = mail;
        this.giorniFerie = giorniFerie;
        this.orePermesso = orePermesso;
        this.oreStraordinario = oreStraordinario;
    }
}
