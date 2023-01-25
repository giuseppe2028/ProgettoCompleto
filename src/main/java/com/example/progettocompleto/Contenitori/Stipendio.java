package com.example.progettocompleto.Contenitori;

public class Stipendio {
    private int impiegato, anno, oreLavorate, saldoOreLavorate,oreStraordinario,saldoOreStraordinario,saldoTrattenute;
    private String mese;
    private double gratificaServizio,salldoGratificaServizio, saldoMaggiorazioneReperibilita,saldoTredicesima,saldoStipendioLordo,saldoStipendioNetto;
    private boolean maggiorazioneReperibilita;
   public Stipendio(int impiegato,String mese, int anno, int oreLavorate,int saldoOreLavorate,double gratificaServizio, double saldoGratificaServizio,int oreStraordinario,int saldoOreStraordinario,boolean maggiorazioneReperibilita, double saldoMaggiorazioneReperibilita, double saldoTredicesima,double saldoStipendioLordo,int saldoTrattenute, double saldoStipendioNetto){
        this.impiegato = impiegato;
        this.mese = mese;
        this.anno = anno;
        this.oreLavorate = oreLavorate;
        this.saldoOreLavorate = saldoOreLavorate;
        this.gratificaServizio= gratificaServizio;
        this.salldoGratificaServizio = saldoGratificaServizio;
        this.oreStraordinario = oreStraordinario;
        this.saldoOreStraordinario = saldoOreStraordinario;
        this.maggiorazioneReperibilita = maggiorazioneReperibilita;
        this.saldoMaggiorazioneReperibilita = saldoMaggiorazioneReperibilita;
        this.saldoTredicesima = saldoTredicesima;
        this.saldoStipendioLordo = saldoStipendioLordo;
        this.saldoTrattenute = saldoTrattenute;
        this.saldoStipendioNetto = saldoStipendioNetto;
    }

    public int getImpiegato() {
        return impiegato;
    }

    public void setImpiegato(int impiegato) {
        this.impiegato = impiegato;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getOreLavorate() {
        return oreLavorate;
    }

    public void setOreLavorate(int oreLavorate) {
        this.oreLavorate = oreLavorate;
    }

    public int getSaldoOreLavorate() {
        return saldoOreLavorate;
    }

    public void setSaldoOreLavorate(int saldoOreLavorate) {
        this.saldoOreLavorate = saldoOreLavorate;
    }

    public int getOreStraordinario() {
        return oreStraordinario;
    }

    public void setOreStraordinario(int oreStraordinario) {
        this.oreStraordinario = oreStraordinario;
    }

    public int getSaldoOreStraordinario() {
        return saldoOreStraordinario;
    }

    public void setSaldoOreStraordinario(int saldoOreStraordinario) {
        this.saldoOreStraordinario = saldoOreStraordinario;
    }

    public int getSaldoTrattenute() {
        return saldoTrattenute;
    }

    public void setSaldoTrattenute(int saldoTrattenute) {
        this.saldoTrattenute = saldoTrattenute;
    }

    public String getMese() {
        return mese;
    }

    public void setMese(String mese) {
        this.mese = mese;
    }

    public double getGratificaServizio() {
        return gratificaServizio;
    }

    public void setGratificaServizio(double gratificaServizio) {
        this.gratificaServizio = gratificaServizio;
    }

    public double getSalldoGratificaServizio() {
        return salldoGratificaServizio;
    }

    public void setSalldoGratificaServizio(double salldoGratificaServizio) {
        this.salldoGratificaServizio = salldoGratificaServizio;
    }

    public double getSaldoMaggiorazioneReperibilita() {
        return saldoMaggiorazioneReperibilita;
    }

    public void setSaldoMaggiorazioneReperibilita(double saldoMaggiorazioneReperibilita) {
        this.saldoMaggiorazioneReperibilita = saldoMaggiorazioneReperibilita;
    }

    public double getSaldoTredicesima() {
        return saldoTredicesima;
    }

    public void setSaldoTredicesima(double saldoTredicesima) {
        this.saldoTredicesima = saldoTredicesima;
    }

    public double getSaldoStipendioLordo() {
        return saldoStipendioLordo;
    }

    public void setSaldoStipendioLordo(double saldoStipendioLordo) {
        this.saldoStipendioLordo = saldoStipendioLordo;
    }

    public double getSaldoStipendioNetto() {
        return saldoStipendioNetto;
    }

    public void setSaldoStipendioNetto(double saldoStipendioNetto) {
        this.saldoStipendioNetto = saldoStipendioNetto;
    }

    public boolean isMaggiorazioneReperibilita() {
        return maggiorazioneReperibilita;
    }

    public void setMaggiorazioneReperibilita(boolean maggiorazioneReperibilita) {
        this.maggiorazioneReperibilita = maggiorazioneReperibilita;
    }
    public String toString(){
       return String.valueOf(getImpiegato());
    }
    public boolean isNull(){
       if(mese == null ){
           return true;
       }
       else{
           return false;
       }
   }
}
