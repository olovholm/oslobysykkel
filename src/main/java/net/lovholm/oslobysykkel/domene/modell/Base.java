package net.lovholm.oslobysykkel.domene.modell;


import java.time.LocalDateTime;

public abstract class Base {

    private LocalDateTime opprettet;
    private LocalDateTime oppdatert;

    protected Base() {
        this.opprettet = LocalDateTime.now();
        this.oppdatert = LocalDateTime.now();
    }

    public LocalDateTime getOpprettet() {
        return opprettet;
    }

    public LocalDateTime getOppdatert() {
        return oppdatert;
    }

    public void setOppdatert(LocalDateTime oppdatert) {
        this.oppdatert = oppdatert;
    }
}
