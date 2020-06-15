package net.lovholm.oslobysykkel.domene.modell;

import java.time.LocalDateTime;

public class Stasjon {
        private String stasjonsId;
        private String navn;
        private String adresse;
        private Integer kapasitet;
        private Integer antallLedigeLåser;
        private Integer antallLedigeSykler;
        private Posisjon posisjon;
        private LocalDateTime sistOppdatertStatus;
        private LocalDateTime sistOppdatertStasjonsinformasjon;

        public Stasjon(){}

        public Stasjon(String stasjonsId, String navn, String adresse, Posisjon posisjon, Integer kapasitet) {
                this.stasjonsId = stasjonsId;
                this.navn = navn;
                this.adresse = adresse;
                this.posisjon = posisjon;
                this.kapasitet = kapasitet;
                this.sistOppdatertStasjonsinformasjon = LocalDateTime.now();
        }

        public String getStasjonsId() {
                return stasjonsId;
        }

        public String getNavn() {
                return navn;
        }

        public String getAdresse() {
                return adresse;
        }

        public Posisjon getPosisjon(){ return posisjon;}

        public Integer getKapasitet() {
                return kapasitet;
        }

        public Integer getAntallLedigeLåser() {
                return antallLedigeLåser;
        }

        public Integer getAntallLedigeSykler() {
                return antallLedigeSykler;
        }

        public LocalDateTime getSistOppdatertStatus() {
                return sistOppdatertStatus;
        }

        public LocalDateTime getSistOppdatertStasjonsinformasjon() {
                return sistOppdatertStasjonsinformasjon;
        }



        public void oppdaterStasjonstatus(Integer antallLedigeLåser, Integer antallLedigeSykler){
                this.sistOppdatertStatus = LocalDateTime.now();
                this.antallLedigeLåser = antallLedigeLåser;
                this.antallLedigeSykler = antallLedigeSykler;
        }



}
