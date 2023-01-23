package com.example.progettocompleto.Contenitori;


public class Impiegati {
    private int matricola;
    private String nome,cognome,ruolo;
    private int servizio;

    public Impiegati(int matricola,String nome,String cognome, int servizio,String ruolo){
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
        this.servizio = servizio;
    }

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public int getServizio() {
        return servizio;
    }

    public void setServizio(int servizio) {
        this.servizio = servizio;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}