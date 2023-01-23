package com.example.progettocompleto.FileDiSistema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CodiceFiscaleCalculator {
    ArrayList<String> codiceComuni = new ArrayList<>();
    String line;
    BufferedReader reader;



    private String nome,cognome;
    char[] consonants = {'b','c','d','f','g','h','l','m','j','k','n','p','q','r','s','t','v','w','x','y','z'};
    char[] vocals = {'a','e','i','o','u'};

    ArrayList<Integer> arrayList = new ArrayList<>();
    ArrayList<Integer> arrayListVocali = new ArrayList<>();
    //TODO sistemare la stampa sbagliata + aggiungere comune e codice di controllo
    public String computeCodiceFiscale(String nome, String cognome, LocalDate dataNascita, boolean sesso, String comune){
        String codiceFiscale = "";
        codiceFiscale = computeNomeCognome(nome,cognome);
        int giorno = dataNascita.getDayOfMonth();
        int mese = dataNascita.getMonthValue();
        int anno = dataNascita.getYear()%100;
        String meseCF = computeMese(mese);
        if(!sesso) {
            giorno += 40;
        }
        //aggiungo l'anno al codice fiscale:
        if(anno<10)
            codiceFiscale = codiceFiscale.concat("0"+Integer.toString(anno));
        else
            codiceFiscale= codiceFiscale.concat(Integer.toString(anno));
        //aggiungo il mese al codice fiscale
        codiceFiscale =  codiceFiscale.concat(meseCF);
        //aggiungo il giorno al codice fiscale
        if(giorno<10)
            codiceFiscale = codiceFiscale.concat("0"+Integer.toString(giorno));
        else
            codiceFiscale = codiceFiscale.concat(Integer.toString(giorno));
        //calcolo il codice di controllo:
        codiceFiscale = codiceFiscale.toUpperCase();
        codiceFiscale = codiceFiscale.concat(getCodiceComune(comune));
        codiceFiscale = codiceFiscale.concat(String.valueOf(codiceControllo(codiceFiscale)));
        return codiceFiscale;
    }

    private String computeNomeCognome(String nome, String cognome){
        System.out.println(countVocali("asia"));
        System.out.println(countConsonant("asia"));
        //fare il controllo sullo spazio
        String codiceFiscale = "";
        //elimino eventuali lettere maiuscole
        this.nome = nome.toLowerCase();
        this.cognome = cognome.toLowerCase();
        //Calcolo quante consonanti ci sono all'interno del cognome,
        //e secondo le leggi eseguo dei calcoli
        if(countConsonant(this.cognome) >= 3){
            //in arrayList salvo la posizione della consonante, e quindi faccio ritornare quella consonante
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayList.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayList.get(1))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayList.get(2))));
        }
        else if(countConsonant(this.cognome) ==2){
            countVocali(this.cognome);
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayList.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayList.get(1))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayListVocali.get(0))));
        }
        else if(countConsonant(this.cognome) == 1 && countVocali(this.cognome) == 2){
            //prendo la consonante
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayList.get(0))));
            //prendo le due vocali
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayListVocali.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayListVocali.get(1))));

        }
        else if(countConsonant(this.cognome) == 1 && countVocali(this.cognome) ==1){
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayList.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayListVocali.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt('x')));
        }
        else{
            countConsonant(this.cognome);
            countVocali(this.cognome);
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayListVocali.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt(arrayListVocali.get(1))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(cognome.charAt('x')));
        }
        if(countConsonant(this.nome) >= 4){
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(2))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(3))));

        }
        else if(countConsonant(this.nome) == 3){
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(1))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(2))));
        }
        else if(countConsonant(this.nome) == 2){
            countVocali(this.nome);
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(1))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayListVocali.get(0))));
        }
        else if(countConsonant(this.nome) == 1 && countVocali(this.nome) >= 2){
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayListVocali.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayListVocali.get(1))));
        }
        else if(countConsonant(this.nome) == 1 && countVocali(this.nome) == 1){
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayList.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayListVocali.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt('x')));
        }

        else {
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayListVocali.get(0))));
            codiceFiscale = codiceFiscale.concat(String.valueOf(nome.charAt(arrayListVocali.get(1))));
            codiceFiscale = codiceFiscale.concat(String.valueOf('x'));
        }

        return codiceFiscale;
    }
    private int countConsonant(String parola){
        int count = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i<parola.length(); i++){
            for(int j= 0; j<consonants.length; j++){
                if(parola.charAt(i) == consonants[j]){
                    count ++;
                    //memorizzo la posizione della consonante
                    arrayList.add(i);
                }
            }
        }
        this.arrayList = arrayList;
        return count;
    }
    //calcola le vocali
    private int countVocali(String parola){
        int count = 0;
        ArrayList<Integer> arrayListVocali = new ArrayList<>();
        for(int i = 0; i<parola.length(); i++){
            for(int j= 0; j<vocals.length; j++){
                if(parola.charAt(i) == vocals[j]){
                    count ++;
                    //memorizzo la posizione della consonante
                    arrayListVocali.add(i);
                }
            }
        }
        this.arrayListVocali = arrayListVocali;
        return count;
    }
    public String computeMese(int mese){
        switch (mese){
            case 1:
                return "A";
            case 2:
                return "B";

            case 3:
                return "C";

            case 4:
                return "D";

            case 5:
                return "E";

            case 6:
                return "H";

            case 7:
                return "L";

            case 8:
                return "M";

            case 9:
                return "P";

            case 10:
                return "R";

            case 11:
                return "S";

            case 12:
                return "T";

        }
        return null;
    }
    private String getCodiceComune(String comune){
        Map<String,String> comuni = new HashMap<>();
        {
            try {
                reader = new BufferedReader(new FileReader("/Users/giuseppebarone/Desktop/elenco.csv"));
                while((line= reader.readLine())!=null){
                    String[] cells = line.split(";");
                    comuni.put(cells[5],cells[18]);
                }
                if(comuni.containsKey(comune)){
                    //mi faccio dare l'indice in cui si trova
                    //faccio ritornare l'indice di questa stringa e successivamente ritorno la stringa in codice di questo comune
                    return comuni.get(comune);
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "";
    }
    private char codiceControllo(String codiceFiscale){
        //inizializzo i le mappe per i caratteri pari e dispari
        //inizializzo una mappa valore, carattere:
        Map<Character,Integer> mappaControlloPari = new HashMap<>();
        Map<Character,Integer> mappaControlloDispari = new HashMap<>();
        Map<Integer,Character> mappaControlloRitorno = new HashMap<>();
        mappaControlloPari.put('0',0);
        mappaControlloPari.put('1',1);
        mappaControlloPari.put('2',2);
        mappaControlloPari.put('3',3);
        mappaControlloPari.put('4',4);
        mappaControlloPari.put('5',5);
        mappaControlloPari.put('6',6);
        mappaControlloPari.put('7',7);
        mappaControlloPari.put('8',8);
        mappaControlloPari.put('9',9);
        mappaControlloPari.put('A',0);
        mappaControlloPari.put('B',1);
        mappaControlloPari.put('C',2);
        mappaControlloPari.put('D',3);
        mappaControlloPari.put('E',4);
        mappaControlloPari.put('F',5);
        mappaControlloPari.put('G',6);
        mappaControlloPari.put('H',7);
        mappaControlloPari.put('I',8);
        mappaControlloPari.put('J',9);
        mappaControlloPari.put('K',10);
        mappaControlloPari.put('L',11);
        mappaControlloPari.put('M',12);
        mappaControlloPari.put('N',13);
        mappaControlloPari.put('O',14);
        mappaControlloPari.put('P',15);
        mappaControlloPari.put('Q',16);
        mappaControlloPari.put('R',17);
        mappaControlloPari.put('S',18);
        mappaControlloPari.put('T',19);
        mappaControlloPari.put('U',20);
        mappaControlloPari.put('V',21);
        mappaControlloPari.put('W',22);
        mappaControlloPari.put('X',23);
        mappaControlloPari.put('Y',24);
        mappaControlloPari.put('Z',25);
        mappaControlloDispari.put('0',1);
        mappaControlloDispari.put('1',0);
        mappaControlloDispari.put('2',5);
        mappaControlloDispari.put('3',7);
        mappaControlloDispari.put('4',9);
        mappaControlloDispari.put('5',13);
        mappaControlloDispari.put('6',15);
        mappaControlloDispari.put('7',17);
        mappaControlloDispari.put('8',19);
        mappaControlloDispari.put('9',21);
        mappaControlloDispari.put('A',1);
        mappaControlloDispari.put('B',0);
        mappaControlloDispari.put('C',5);
        mappaControlloDispari.put('D',7);
        mappaControlloDispari.put('E',9);
        mappaControlloDispari.put('F',13);
        mappaControlloDispari.put('G',15);
        mappaControlloDispari.put('H',17);
        mappaControlloDispari.put('I',19);
        mappaControlloDispari.put('J',21);
        mappaControlloDispari.put('K',2);
        mappaControlloDispari.put('L',4);
        mappaControlloDispari.put('M',18);
        mappaControlloDispari.put('N',20);
        mappaControlloDispari.put('O',11);
        mappaControlloDispari.put('P',3);
        mappaControlloDispari.put('Q',6);
        mappaControlloDispari.put('R',8);
        mappaControlloDispari.put('S',12);
        mappaControlloDispari.put('T',14);
        mappaControlloDispari.put('U',16);
        mappaControlloDispari.put('V',10);
        mappaControlloDispari.put('W',22);
        mappaControlloDispari.put('X',25);
        mappaControlloDispari.put('Y',24);
        mappaControlloDispari.put('Z',23);


        mappaControlloRitorno.put(0,'A');
        mappaControlloRitorno.put(1,'B');
        mappaControlloRitorno.put(2,'C');
        mappaControlloRitorno.put(3,'D');
        mappaControlloRitorno.put(4,'E');
        mappaControlloRitorno.put(5,'F');
        mappaControlloRitorno.put(6,'G');
        mappaControlloRitorno.put(7,'H');
        mappaControlloRitorno.put(8,'I');
        mappaControlloRitorno.put(9,'J');
        mappaControlloRitorno.put(10,'K');
        mappaControlloRitorno.put(11,'L');
        mappaControlloRitorno.put(12,'M');
        mappaControlloRitorno.put(13,'N');
        mappaControlloRitorno.put(14,'O');
        mappaControlloRitorno.put(15,'P');
        mappaControlloRitorno.put(16,'Q');
        mappaControlloRitorno.put(17,'R');
        mappaControlloRitorno.put(18,'S');
        mappaControlloRitorno.put(19,'T');
        mappaControlloRitorno.put(20,'U');
        mappaControlloRitorno.put(21,'V');
        mappaControlloRitorno.put(22,'W');
        mappaControlloRitorno.put(23,'X');
        mappaControlloRitorno.put(24,'Y');
        mappaControlloRitorno.put(25,'Z');



        int totale = 0;
        //prendo i valori dei caratteri pari
        for(int i = 0; i<codiceFiscale.length(); i+=2){
            totale += mappaControlloPari.get(codiceFiscale.charAt(i));
        }
        System.out.println(totale);
        for(int i = 1; i<codiceFiscale.length();i+=2){
            totale += mappaControlloDispari.get(codiceFiscale.charAt(i));
        }
        int value = totale%26;


        return mappaControlloRitorno.get(value);
    }
}
