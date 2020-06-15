package net.lovholm.oslobysykkel.api;

import net.lovholm.oslobysykkel.api.dto.StasjonsstatusResponse;
import net.lovholm.oslobysykkel.api.dto.StasjonstatusDto;
import net.lovholm.oslobysykkel.domene.modell.Stasjon;
import net.lovholm.oslobysykkel.domene.tjeneste.Stasjonstjeneste;
import net.lovholm.oslobysykkel.oppdaterer.Oppdaterertjeneste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(name = "lon", required = false) Double lon,
            @RequestParam(name = "lat", required = false) Double lat,
            @RequestParam(name = "antall", required = false) Integer antall) {


        oppdaterertjeneste.oppdaterStasjonsstatus();
        var response = new StasjonsstatusResponse();
        response.setStasjoner(
                stasjonstjeneste.hentAlleStasjoner().stream().map(this::mapStasjonsstatusDtoFraStasjon).collect(Collectors.toList()
                ).subList(0,antall));
        return response;
    }

    private StasjonstatusDto mapStasjonsstatusDtoFraStasjon(Stasjon stasjon) {
        return new StasjonstatusDto(
                stasjon.getStasjonsId(),
                stasjon.getNavn(),
                stasjon.getAdresse(),
                null, //TODO: Mappe inn avstand
                stasjon.getPosisjon().getLat(),
                stasjon.getPosisjon().getLon(),
                stasjon.getKapasitet(),
                stasjon.getStatus().getAntallLedigeLåser(),
                stasjon.getStatus().getAntallLedigeSykler()
        );
    }

}
