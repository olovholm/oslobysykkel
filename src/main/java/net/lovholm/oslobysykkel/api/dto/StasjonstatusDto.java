package net.lovholm.oslobysykkel.api.dto;

public class StasjonstatusDto {
    String stasjonsId;
    String navn;
    String adresse;
    Double lat;
    Double lon;
    Integer kapasitet;
    Integer antallLedigeLåser;
    Integer antallLedigeSykler;

    public StasjonstatusDto(String stasjonsId, String navn, String adresse, Double lat, Double lon, Integer kapasitet, Integer antallLedigeLåser, Integer antallLedigeSykler) {
        this.stasjonsId = stasjonsId;
        this.navn = navn;
        this.adresse = adresse;
        this.lat = lat;
        this.lon = lon;
        this.kapasitet = kapasitet;
        this.antallLedigeLåser = antallLedigeLåser;
        this.antallLedigeSykler = antallLedigeSykler;
    }

    public String getStasjonsId() {
        return stasjonsId;
    }

    public void setStasjonsId(String stasjonsId) {
        this.stasjonsId = stasjonsId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getKapasitet() {
        return kapasitet;
    }

    public void setKapasitet(Integer kapasitet) {
        this.kapasitet = kapasitet;
    }

    public Integer getAntallLedigeLåser() {
        return antallLedigeLåser;
    }

    public void setAntallLedigeLåser(Integer antallLedigeLåser) {
        this.antallLedigeLåser = antallLedigeLåser;
    }

    public Integer getAntallLedigeSykler() {
        return antallLedigeSykler;
    }

    public void setAntallLedigeSykler(Integer antallLedigeSykler) {
        this.antallLedigeSykler = antallLedigeSykler;
    }
}
