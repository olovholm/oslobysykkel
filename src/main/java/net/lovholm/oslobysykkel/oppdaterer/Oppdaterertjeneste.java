package net.lovholm.oslobysykkel.oppdaterer;


import net.lovholm.oslobysykkel.domene.tjeneste.Stasjonstjeneste;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Oppdaterertjeneste {
    private final static Logger log = LoggerFactory.getLogger(Oppdaterertjeneste.class);

    private final static int VENTETID_OPPDATERE_STASJONER_MILLIS_15_MINUTTER = 60_000 * 15;
    private final static int VENTETID_SEKUNDER_OPPDATERING_STASJONSSTATUS = 30;

    private static LocalDateTime SIST_OPPDATERT_STATUS;

    private final Stasjonstjeneste stasjonstjeneste;

    @Autowired
    public Oppdaterertjeneste(Stasjonstjeneste stasjonstjeneste){
        this.stasjonstjeneste = stasjonstjeneste;
        SIST_OPPDATERT_STATUS = LocalDateTime.now();
    }


    @Scheduled(fixedRate = VENTETID_OPPDATERE_STASJONER_MILLIS_15_MINUTTER, initialDelay = 200)
    public void oppdaterStasjonerOgStatus(){
        log.info("Oppdaterer stasjoner");
        stasjonstjeneste.hentOgLagreAlleStasjoner();
        stasjonstjeneste.oppdaterStasjonerMedStatusInformasjon();
    }

    public void oppdaterStasjonsstatus() {
        if(SIST_OPPDATERT_STATUS.isBefore(LocalDateTime.now().minusSeconds(VENTETID_SEKUNDER_OPPDATERING_STASJONSSTATUS))) {
            SIST_OPPDATERT_STATUS = LocalDateTime.now();
            log.debug("Oppdaterer status");
            stasjonstjeneste.oppdaterStasjonerMedStatusInformasjon();
        } else {
            log.debug("Henter ikke inn stasjonsstatus på nytt");
        }
    }


}
