package net.lovholm.oslobysykkel.domene.modell;

import java.time.LocalDateTime;

public class Stasjon extends Base {
        private String stasjonsId;
        private String navn;
        private String adresse;
        private Integer kapasitet;
        private Posisjon posisjon;
        private Status status;


        public Stasjon(){}

        public Stasjon(String stasjonsId, String navn, String adresse, Posisjon posisjon, Integer kapasitet) {
                this.stasjonsId = stasjonsId;
                this.navn = navn;
                this.adresse = adresse;
                this.posisjon = posisjon;
                this.kapasitet = kapasitet;
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

        public Status getStatus() {
                return status;
        }

        public void setStatus(Status status){
                super.setOppdatert(LocalDateTime.now());
                this.status = status;
        }

}
