package com.example.progettocompleto.Contenitori;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;

public class Richiesta{
    private int id,ref_impiegato,matricola_destinazione;
    private String categoria,stato,svolgimento,motivazione,tipologia,tipo_turno_origine,tipo_turno_destinazione;
    private LocalDate data_inizio,data_fine,data_turno_origine,data_turno_destinazione;
    private LocalTime ora_inizio,ora_fine;
    private Blob allegato;

    public Richiesta(int id, String categoria, String stato, LocalDate data_inizio, LocalDate data_fine, LocalTime ora_inizio, LocalTime ora_fine, String svolgimento, String motivazione, String tipologia, int matricola_destinazione, String tipo_turno_origine, String tipo_turno_destinazione, LocalDate data_turno_origine, LocalDate data_turno_destinazione){
        this.id=id;
        this.ref_impiegato=ref_impiegato;
        this.categoria=categoria;
        this.stato=stato;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
        this.ora_inizio=ora_inizio;
        this.ora_fine=ora_fine;
        this.svolgimento=svolgimento;
        this.motivazione=motivazione;
        this.tipologia=tipologia;
        this.matricola_destinazione=matricola_destinazione;
        this.tipo_turno_origine=tipo_turno_origine;
        this.tipo_turno_destinazione=tipo_turno_destinazione;
        this.data_turno_origine=data_turno_origine;
        this.data_turno_destinazione=data_turno_destinazione;
        this.allegato=allegato;
    }
    public Richiesta(String categoria, int ref_impiegato, String tipo_turno_origine, String tipo_turno_destinazione, LocalDate data_turno_origine, LocalDate data_turno_destinazione){
        this.categoria=categoria;
        this.ref_impiegato=ref_impiegato;
        this.tipo_turno_origine=tipo_turno_origine;
        this.tipo_turno_destinazione=tipo_turno_destinazione;
        this.data_turno_origine=data_turno_origine;
        this.data_turno_destinazione=data_turno_destinazione;
    }
    public Richiesta( int ref_impiegato, LocalDate data_inizio, LocalDate data_fine, String stato, String svolgimento, String motivazione, int id){
        this.ref_impiegato=ref_impiegato;
        this.stato=stato;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
        this.svolgimento=svolgimento;
        this.motivazione=motivazione;
        this.id=id;

    }
    @Override
    public String toString(){
        return Integer.toString(this.id);
    }

    public int getMatricola_destinazione() {
        return matricola_destinazione;
    }

    public void setMatricola_destinazione(int matricola_destinazione) {
        this.matricola_destinazione = matricola_destinazione;
    }

    public int getRef_impiegato() {
        return ref_impiegato;
    }

    public void setRef_impiegato(int ref_impiegato) {
        this.ref_impiegato = ref_impiegato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getSvolgimento() {
        return svolgimento;
    }

    public void setSvolgimento(String svolgimento) {
        this.svolgimento = svolgimento;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getTipo_turno_origine() {
        return tipo_turno_origine;
    }

    public void setTipo_turno_origine(String tipo_turno_origine) {
        this.tipo_turno_origine = tipo_turno_origine;
    }

    public String getTipo_turno_destinazione() {
        return tipo_turno_destinazione;
    }

    public void setTipo_turno_destinazione(String tipo_turno_destinazione) {
        this.tipo_turno_destinazione = tipo_turno_destinazione;
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public LocalDate getData_fine() {
        return data_fine;
    }

    public void setData_fine(LocalDate data_fine) {
        this.data_fine = data_fine;
    }

    public LocalDate getData_turno_origine() {
        return data_turno_origine;
    }

    public void setData_turno_origine(LocalDate data_turno_origine) {
        this.data_turno_origine = data_turno_origine;
    }

    public LocalDate getData_turno_destinazione() {
        return data_turno_destinazione;
    }

    public void setData_turno_destinazione(LocalDate data_turno_destinazione) {
        this.data_turno_destinazione = data_turno_destinazione;
    }

    public LocalTime getOra_inizio() {
        return ora_inizio;
    }

    public void setOra_inizio(LocalTime ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public LocalTime getOra_fine() {
        return ora_fine;
    }

    public void setOra_fine(LocalTime ora_fine) {
        this.ora_fine = ora_fine;
    }

    public Blob getAllegato() {
        return allegato;
    }

    public void setAllegato(Blob allegato) {
        this.allegato = allegato;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
}


