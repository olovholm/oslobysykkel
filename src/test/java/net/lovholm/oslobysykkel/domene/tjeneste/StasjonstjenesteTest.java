package net.lovholm.oslobysykkel.domene.tjeneste;

import net.lovholm.oslobysykkel.domene.modell.Posisjon;
import net.lovholm.oslobysykkel.domene.modell.Stasjon;
import net.lovholm.oslobysykkel.domene.repository.Stasjonsrepository;
import net.lovholm.oslobysykkel.integrasjon.oslobysykkel.klient.GBFSLoadService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

public class StasjonstjenesteTest {

    private static final Stasjon STASJON_1 = new Stasjon("1", "Testgata", "Portveien 2", new Posisjon(1.1,1.1), 40);
    private static final Stasjon STASJON_2 = new Stasjon("2", "Teststedet", "Brugata 17", new Posisjon(1.2,1.2), 20);
    private static final Stasjon STASJON_3 = new Stasjon("3", "Test Test", "Storgata 12", new Posisjon(1.3,1.3), 20);

    private Stasjonstjeneste stasjonstjeneste;


    @Test
    public void skalKunneHenteAlleStasjoner(){
        GBFSLoadService gbfsLoadService = Mockito.mock(GBFSLoadService.class);
        Stasjonsrepository stasjonsrepository = Mockito.mock(Stasjonsrepository.class);
        Mockito.when(stasjonsrepository.getStasjoner()).thenReturn(Arrays.asList(STASJON_1,STASJON_2));

        stasjonstjeneste = new Stasjonstjeneste(gbfsLoadService, stasjonsrepository);
        var stasjoner = stasjonstjeneste.hentAlleStasjoner();

        assertThat("Stasjonstjenesten skal kunne hente alle stasjoner",stasjoner, hasItems(STASJON_1,STASJON_2));
    }






}
