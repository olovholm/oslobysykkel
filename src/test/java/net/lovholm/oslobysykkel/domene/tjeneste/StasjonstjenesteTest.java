package net.lovholm.oslobysykkel.domene.tjeneste;

import net.lovholm.oslobysykkel.domene.modell.Posisjon;
import net.lovholm.oslobysykkel.domene.modell.Stasjon;
import net.lovholm.oslobysykkel.domene.repository.Stasjonsrepository;
import net.lovholm.oslobysykkel.integrasjon.oslobysykkel.klient.GBFSLoadService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;

public class StasjonstjenesteTest {

    private static final Posisjon ULAN_BATOR = new Posisjon(47.8864,106.9057);
    private static final Posisjon TØYEN = new Posisjon(59.9148,10.7673);
    private static final Posisjon PRAHA = new Posisjon(10.7673,14.4378);
    private static final Posisjon HELSFYR = new Posisjon(59.9158,10.8046);

    private static final Stasjon STASJON_TØYEN = new Stasjon("1", "TØYEN", "Portveien 2", TØYEN, 40);
    private static final Stasjon STASJON_ULAN_BATOR = new Stasjon("2", "ULAN_BATOR", "Brugata 17", ULAN_BATOR, 20);
    private static final Stasjon STASJON_PRAHA = new Stasjon("3", "PRAHA", "Storgata 12", PRAHA, 20);

    private Stasjonstjeneste stasjonstjeneste;


    @Test
    public void skalKunneHenteAlleStasjoner(){
        GBFSLoadService gbfsLoadService = Mockito.mock(GBFSLoadService.class);
        Stasjonsrepository stasjonsrepository = Mockito.mock(Stasjonsrepository.class);
        Mockito.when(stasjonsrepository.getStasjonerCopy()).thenReturn(Arrays.asList(STASJON_TØYEN, STASJON_ULAN_BATOR));

        stasjonstjeneste = new Stasjonstjeneste(gbfsLoadService, stasjonsrepository);
        var stasjoner = stasjonstjeneste.hentAlleStasjoner();

        assertThat("Stasjonstjenesten skal kunne hente alle stasjoner",stasjoner, hasItems(STASJON_TØYEN, STASJON_ULAN_BATOR));
    }

    @Test
    public void skalSortereStasjonerEtterAvstand(){
        GBFSLoadService gbfsLoadService = Mockito.mock(GBFSLoadService.class);
        Stasjonsrepository stasjonsrepository = Mockito.mock(Stasjonsrepository.class);
        Mockito.when(stasjonsrepository.getStasjonerCopy()).thenReturn(Arrays.asList(STASJON_TØYEN, STASJON_ULAN_BATOR, STASJON_PRAHA));

        stasjonstjeneste = new Stasjonstjeneste(gbfsLoadService, stasjonsrepository);
        var stasjoner = stasjonstjeneste.hentNærmesteStasjoner(HELSFYR);

        assertThat("Stasjonstjenesten skal hente stasjoner ordnet etter nærhet til posisjon", stasjoner, contains(STASJON_TØYEN, STASJON_PRAHA, STASJON_ULAN_BATOR));


    }






}
