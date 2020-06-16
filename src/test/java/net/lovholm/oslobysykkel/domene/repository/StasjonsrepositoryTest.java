package net.lovholm.oslobysykkel.domene.repository;


import net.lovholm.oslobysykkel.domene.modell.Posisjon;
import net.lovholm.oslobysykkel.domene.modell.Stasjon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StasjonsrepositoryTest {

    private static final Stasjon STASJON_1 = new Stasjon("1", "Testgata", "Portveien 2", new Posisjon(1.1,1.1), 40);
    private static final Stasjon STASJON_2 = new Stasjon("2", "Teststedet", "Brugata 17", new Posisjon(1.2,1.2), 20);
    private static final Stasjon STASJON_3 = new Stasjon("3", "Test Test", "Storgata 12", new Posisjon(1.3,1.3), 20);

    private Stasjonsrepository stasjonsrepository;

    @BeforeEach
    public void setup(){
        stasjonsrepository = new Stasjonsrepository();
    }

    @Test
    public void skalKunneSetteStasjonerIBolk(){
        List<Stasjon> stasjonList = Arrays.asList(STASJON_1,STASJON_2,STASJON_3);
        stasjonsrepository.settStasjoner(stasjonList);
        assertThat("Stasjonsrepository skal kunne sette alle stasjoner",stasjonsrepository.getStasjonerCopy(), hasItems(STASJON_1,STASJON_2,STASJON_3));
    }

    @Test
    public void skalKunneSetteStasjonerEnkeltvis(){
        stasjonsrepository.leggTilStasjon(STASJON_1);
        assertThat("Enkeltstasjon skal kunne legges til stasjonsrepository", stasjonsrepository.getStasjonerCopy(),hasItem(STASJON_1));
    }

    @Test
    public void skalKunneHenteStasjonBasertPåId(){
        stasjonsrepository.leggTilStasjon(STASJON_1);
        stasjonsrepository.leggTilStasjon(STASJON_2);
        var stasjon = stasjonsrepository.getStasjonById(STASJON_2.getStasjonsId()).get();
        assertThat("Skal kunne finne stasjon basert på stasjonsId", stasjon, sameInstance(STASJON_2));
    }

    @Test
    public void skalKunneEndreHentetListeUtenAtOpprinneligListeEndres(){
        stasjonsrepository.settStasjoner(Arrays.asList(STASJON_1,STASJON_2,STASJON_3));
        List<Stasjon> stasjonsListeMuteres = stasjonsrepository.getStasjonerCopy();
        stasjonsListeMuteres.add(new Stasjon("43","Test","Adresse",null,0));
        List<Stasjon> stasjonsListeNyinnhentet = stasjonsrepository.getStasjonerCopy();
        assertThat("Opprinnelig liste skal ikke kunne muteres etter uthenting",stasjonsListeNyinnhentet.size(),equalTo(3));
    }
}
