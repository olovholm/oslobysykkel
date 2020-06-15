package net.lovholm.oslobysykkel.domene.modell;

public class Status extends Base {

    private final Integer antallLedigeSykler;
    private final Integer antallLedigeLåser;

    public Status(Integer antallLedigeSykler, Integer antallLedigeLåser){
        this.antallLedigeSykler = antallLedigeSykler;
        this.antallLedigeLåser = antallLedigeLåser;

    }

    public Integer getAntallLedigeSykler() {
        return antallLedigeSykler;
    }

    public Integer getAntallLedigeLåser() {
        return antallLedigeLåser;
    }
}
