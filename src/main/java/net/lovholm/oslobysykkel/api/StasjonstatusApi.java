package net.lovholm.oslobysykkel.api;

import net.lovholm.oslobysykkel.api.dto.StasjonsstatusResponse;
import net.lovholm.oslobysykkel.api.dto.StasjonstatusDto;
import net.lovholm.oslobysykkel.domene.modell.Stasjon;
import net.lovholm.oslobysykkel.domene.tjeneste.Stasjonstjeneste;
import net.lovholm.oslobysykkel.oppdaterer.Oppdaterertjeneste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
public class StasjonstatusApi {

    private final Stasjonstjeneste stasjonstjeneste;
    private final Oppdaterertjeneste oppdaterertjeneste;

    @Autowired
    public StasjonstatusApi(Stasjonstjeneste stasjonstjeneste, Oppdaterertjeneste oppdaterertjeneste) {
        this.stasjonstjeneste = stasjonstjeneste;
        this.oppdaterertjeneste = oppdaterertjeneste;
    }


    @GetMapping("/")
    public StasjonsstatusResponse getStasjonsstatus(
            HttpServletRequest request,
            @RequestParam(name = "lon", required = false) String lon,
            @RequestParam(name = "lat", required = false) String lat,
            @RequestParam(name = "antall", required = false) String antall) {
        oppdaterertjeneste.oppdaterStasjonsstatus();
        var response = new StasjonsstatusResponse();
        response.setStasjoner(
                stasjonstjeneste.hentAlleStasjoner().stream().map(this::mapStasjonsstatusDtoFraStasjon).collect(Collectors.toList()
                ));
        return response;
    }

    private StasjonstatusDto mapStasjonsstatusDtoFraStasjon(Stasjon stasjon) {
        return new StasjonstatusDto(
                stasjon.getStasjonsId(),
                stasjon.getNavn(),
                stasjon.getAdresse(),
                stasjon.getLat(),
                stasjon.getLon(),
                stasjon.getKapasitet(),
                stasjon.getAntallLedigeLåser(),
                stasjon.getAntallLedigeSykler()
        );
    }

}
