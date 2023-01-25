package com.example.progettocompleto.Contenitori;

import java.time.LocalDate;

public class Turno {



        private int id;
        private String dipendente;
        private String turno;
        private int servizio;
        private String ruolo;
        private LocalDate dataTurno;

        public Turno(String turno, String dipendente, int refServizio, String ruolo , LocalDate dataTurno){


            this.dataTurno = dataTurno;
            this.dipendente = dipendente;
            this.servizio = refServizio;
            this.ruolo = ruolo;
            this.turno = turno;

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }



        public String getTurno() {
            return turno;
        }

        public void setTipoTurno(String turno) {
            this.turno = turno;
        }



        public LocalDate getDataTurno() {
            return dataTurno;
        }

        public void setDataTurno(LocalDate dataTurno) {
            this.dataTurno = dataTurno;
        }

        public int getServizio() {
            return servizio;
        }

        public void setServizio(int servizio) {
            this.servizio = servizio;
        }

        public String getDipendente() {
            return dipendente;
        }

        public void setdipendente(String dipendente) {
            this.dipendente = dipendente;
        }

        public String getRuolo() {
            return ruolo;
        }

        public void setRuolo(String ruolo) {
            this.ruolo = ruolo;
        }

    }
