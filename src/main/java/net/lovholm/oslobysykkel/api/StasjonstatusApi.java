package net.lovholm.oslobysykkel.api;

import net.lovholm.oslobysykkel.api.dto.StasjonsstatusResponse;
import net.lovholm.oslobysykkel.api.dto.StasjonstatusDto;
import net.lovholm.oslobysykkel.domene.modell.Stasjon;
import net.lovholm.oslobysykkel.domene.tjeneste.Stasjonstjeneste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
public class StasjonstatusApi {

    private final Stasjonstjeneste stasjonstjeneste;

    public StasjonstatusApi(@Autowired Stasjonstjeneste stasjonstjeneste) {
        this.stasjonstjeneste = stasjonstjeneste;
    }


    @GetMapping("/")
    public StasjonsstatusResponse getStasjonsstatus() {
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
