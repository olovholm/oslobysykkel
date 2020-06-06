package net.lovholm.oslobysykkel.domene.modell;

import java.time.LocalDateTime;

public class Stasjon {
        String stasjonsId;
        String navn;
        String adresse;
        Double lat;
        Double lon;
        Integer kapasitet;
        Integer antallLedigeLåser;
        Integer antallLedigeSykler;
        LocalDateTime sistOppdatertStatus;
        LocalDateTime sistOppdatertStasjonsinformasjon;

        public Stasjon(){}

        public Stasjon(String stasjonsId, String navn, String adresse, Double lat, Double lon, Integer kapasitet) {
                this.stasjonsId = stasjonsId;
                this.navn = navn;
                this.adresse = adresse;
                this.lat = lat;
                this.lon = lon;
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

        public Double getLat() {
                return lat;
        }

        public Double getLon() {
                return lon;
        }

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
