package com.example.progettocompleto.FileDiSistema;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntityUtente {
    private static int matricola;
    private static String nome;
    private static String cognome;
    private static String cf;
    private static Date data_nascita;

    private static Blob foto_profilo;
    private static String indirizzo_residenza;
    private static String ruolo;
    private static String mail;
    private String password;
    private static String IBAN;
    private static long recapito_telefonico;
    private static String mail_personale;
    private static InputStream inputStream;
    private static String sesso;
    private static Integer servizio;


    public EntityUtente(ArrayList<Object> arrayList) {
        this.matricola = (Integer) arrayList.get(0);
        this.nome = (String) arrayList.get(1);
        this.cognome = (String) arrayList.get(2);
        this.cf = (String) arrayList.get(3);
        this.data_nascita = (Date) arrayList.get(4);
        // this.foto_profilo = (Blob) arrayList.get(5);
        this.indirizzo_residenza = (String) arrayList.get(5);
        this.ruolo = (String) arrayList.get(6);
        this.mail = (String) arrayList.get(7);
        //  this.password = (String) arrayList.get(8);
        this.IBAN = (String) arrayList.get(8);
        this.recapito_telefonico = (long) arrayList.get(9);
        this.mail_personale = (String) arrayList.get(10);
        // this.inputStream= (InputStream) arrayList.get(11);
        this.sesso = (String) arrayList.get(11);
        ;
        this.servizio = (Integer) arrayList.get(12);

    }

    public static List<Object> getDatiProfilo() {
        List<Object> lista = new ArrayList<>();
        lista.add(matricola);
        lista.add(nome);
        lista.add(cognome);
        lista.add(cf);
        lista.add(data_nascita);
        //lista.add(foto_profilo);
        lista.add(indirizzo_residenza);
        lista.add(ruolo);
        lista.add(mail);
        lista.add(IBAN);
        lista.add(recapito_telefonico);
        lista.add(mail_personale);
        lista.add(inputStream);
        lista.add(servizio);

        return lista;
    }

    public static int getMatricola() {
        return matricola;
    }

    public String toString() {
        String a = matricola + nome + cognome + cf + data_nascita + foto_profilo + indirizzo_residenza + ruolo + mail + password + IBAN + recapito_telefonico + mail_personale;
        return a;
    }

    public static String getSesso() {
        return sesso;
    }


}