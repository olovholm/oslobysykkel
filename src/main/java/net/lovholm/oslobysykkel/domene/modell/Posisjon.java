package net.lovholm.oslobysykkel.domene.modell;

import net.lovholm.oslobysykkel.domene.Haversine;

public final class Posisjon extends Base {
    private final Double lat;
    private final Double lon;

    public Posisjon(Double lat, Double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public Double distanseFra(Posisjon annenPosisjon){
        return Haversine.haversine(this.lat, this.lon, annenPosisjon.lat, annenPosisjon.lon);
    }

}
