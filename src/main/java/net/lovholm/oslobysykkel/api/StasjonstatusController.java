package net.lovholm.oslobysykkel.api;

import net.lovholm.oslobysykkel.api.dto.StasjonsstatusResponse;
import net.lovholm.oslobysykkel.api.dto.StasjonstatusDto;
import net.lovholm.oslobysykkel.domene.modell.Posisjon;
import net.lovholm.oslobysykkel.domene.modell.Stasjon;
import net.lovholm.oslobysykkel.domene.tjeneste.Stasjonstjeneste;
import net.lovholm.oslobysykkel.oppdaterer.Oppdaterertjeneste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
public class StasjonstatusController {

    private final Stasjonstjeneste stasjonstjeneste;
    private final Oppdaterertjeneste oppdaterertjeneste;

    @Autowired
    public StasjonstatusController(Stasjonstjeneste stasjonstjeneste, Oppdaterertjeneste oppdaterertjeneste) {
        this.stasjonstjeneste = stasjonstjeneste;
        this.oppdaterertjeneste = oppdaterertjeneste;
    }

    @GetMapping("stasjonstatus/")
    public StasjonsstatusResponse getStasjonsstatus(
            HttpServletRequest request,
            @RequestParam(name = "lon", required = false) Double lon,
            @RequestParam(name = "lat", required = false) Double lat,
            @RequestParam(name = "antall", required = false, defaultValue = "20") Integer antall) {

        oppdaterertjeneste.oppdaterStasjonsstatus();

        List<Stasjon> stasjoner = null;
        if(harPosisjon(lat,lon)){
            Posisjon posisjon = new Posisjon(lat, lon);
            stasjoner = stasjonstjeneste.hentNærmesteStasjoner(posisjon);
        } else {
            stasjoner = stasjonstjeneste.hentAlleStasjoner();
        }

        var response = new StasjonsstatusResponse();
        var stasjonsatus = stasjoner.stream().map(this::mapStasjonsstatusDtoFraStasjon).collect(Collectors.toList());

        if(stasjonsatus.size() >= antall){
            stasjonsatus =  stasjonsatus.subList(0,antall);
        }
        response.setStasjoner(stasjonsatus);
        return response;
    }


    private boolean harPosisjon (Double lon, Double lat) {
        return (lon != null && lat != null);
    }

    private StasjonstatusDto mapStasjonsstatusDtoFraStasjon(Stasjon stasjon) {
        return new StasjonstatusDto(
                stasjon.getStasjonsId(),
                stasjon.getNavn(),
                stasjon.getAdresse(),
                stasjon.getPosisjon().getLat(),
                stasjon.getPosisjon().getLon(),
                stasjon.getKapasitet(),
                stasjon.getStatus().getAntallLedigeLåser(),
                stasjon.getStatus().getAntallLedigeSykler()
        );
    }

}
