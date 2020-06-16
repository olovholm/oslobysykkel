package net.lovholm.oslobysykkel.domene.repository;

import net.lovholm.oslobysykkel.domene.modell.Stasjon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class Stasjonsrepository {

    private List<Stasjon> stasjoner;

    public Stasjonsrepository(){
        stasjoner = Collections.synchronizedList(new ArrayList<>());
    }

    public List<Stasjon> getStasjonerCopy() {
        return new ArrayList<>(stasjoner);
    }

    public Optional<Stasjon> getStasjonById(String stasjonsId){
        return this.stasjoner.stream().filter(it -> it.getStasjonsId().equalsIgnoreCase(stasjonsId)).findFirst();
    }

    public void settStasjoner(List<Stasjon> stasjoner) {
        this.stasjoner = stasjoner;
    }

    public void leggTilStasjon(Stasjon stasjon){
        this.stasjoner.add(stasjon);
    }


}
