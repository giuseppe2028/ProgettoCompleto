package com.example.progettocompleto.Contenitori;


import java.time.LocalDate;
import java.time.LocalTime;

public class PropostaTurno {


    private int id;
    private String dipendente;
    private static String turno;
    private int refServizio;
    private String ruolo;
    private static LocalDate dataTurno;

    private static LocalTime inizioTurno;

    private static LocalTime fineTurno;

    private static int refImpiegato;

    private int servizio;


    public PropostaTurno(String turno, String dipendente,int refServizio,String ruolo ,LocalDate dataTurno, LocalTime inizioTurno, LocalTime fineTurno, int refImpiegato){
        System.out.println("servizio in proposta turno:" + refServizio);
        this.id = id;
        this.turno = turno;
        this.dataTurno = dataTurno;
        this.dipendente = dipendente;
        this.refServizio = refServizio;
        this.ruolo = ruolo;
        this.inizioTurno = inizioTurno;
        this.fineTurno = fineTurno;
        this.refImpiegato = refImpiegato;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }




    public static LocalDate getDataTurno() {
        return dataTurno;
    }





    public int getServizio() {
        return refServizio;
    }

    public void setServizio(int servizio) {
        this.servizio = servizio;
    }


    public String getDipendente() {
        return dipendente;
    }

    public void setDipendente(String dipendente) {
        this.dipendente = dipendente;
    }


    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }



    public static LocalTime getInizioTurno(){
        return inizioTurno;
    }




    public static LocalTime getFineTurno(){
        return fineTurno;
    }





    public static int getRefImpiegato(){
        return  refImpiegato;
    }



    @Override
    public String toString(){
        return dataTurno.toString() + dipendente + servizio + ruolo + dipendente;
    }

}