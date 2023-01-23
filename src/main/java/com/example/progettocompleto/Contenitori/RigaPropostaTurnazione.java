package com.example.GestioneRemoto.Contenitori;

public class RigaPropostaTurnazione {
    private String turno;
    private String dipendente;
    private int servizio;
    private String ruolo;
    public RigaPropostaTurnazione(String turno, String congnome, int servizio, String ruolo){
        this.turno = turno;
        this.dipendente = congnome;
        this.servizio = servizio;
        this.ruolo = ruolo;

    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDipendente() {
        return dipendente;
    }

    public void setdipendente(String dipendente) {
        this.dipendente = dipendente;
    }

    public int getServizio() {
        return servizio;
    }

    public void setServizio(int servizio) {
        this.servizio = servizio;
    }

    public String getRuolo() {
        return ruolo;
    }
    public void setRuolo(){
        this.ruolo =ruolo;
    }
}
