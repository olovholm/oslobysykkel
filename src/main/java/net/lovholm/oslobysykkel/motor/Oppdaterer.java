package net.lovholm.oslobysykkel.motor;


import net.lovholm.oslobysykkel.domene.tjeneste.Stasjonstjeneste;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Oppdaterer {
    private final static Logger log = LoggerFactory.getLogger(Oppdaterer.class);

    private final static int MILLIS_15_MINUTTER = 60_000 * 15;
    private final static int MILLIS_30_SEKUNDER = 30_000;

    private final Stasjonstjeneste stasjonstjeneste;

    public Oppdaterer(@Autowired Stasjonstjeneste stasjonstjeneste){
        this.stasjonstjeneste = stasjonstjeneste;
    }


    @Scheduled(fixedRate = MILLIS_15_MINUTTER, initialDelay = 200)
    public void oppdaterStasjoner(){
        log.info("Oppdaterer stasjoner");
        stasjonstjeneste.hentOgLagreAlleStasjoner();

    }

    @Scheduled(fixedRate = MILLIS_30_SEKUNDER, initialDelay = 200)
    public void oppdaterStasjonsstatus() {
        log.info("Oppdaterer status");
        stasjonstjeneste.oppdaterStasjonerMedStatusInformasjon();
    }


}
