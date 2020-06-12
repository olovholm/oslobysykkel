package net.lovholm.oslobysykkel.api.dto;

public class StasjonstatusDto {
    private String stasjonsId;
    private String navn;
    private String adresse;
    private Integer avstand;
    private Double lat;
    private Double lon;
    private Integer kapasitet;
    private Integer antallLedigeLåser;
    private Integer antallLedigeSykler;

    public StasjonstatusDto(String stasjonsId, String navn, String adresse, Integer avstand, Double lat, Double lon, Integer kapasitet, Integer antallLedigeLåser, Integer antallLedigeSykler) {
        this.stasjonsId = stasjonsId;
        this.navn = navn;
        this.adresse = adresse;
        this.avstand = avstand;
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

    public Integer getAvstand() {
        return avstand;
    }

    public void setAvstand(Integer avstand) {
        this.avstand = avstand;
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
