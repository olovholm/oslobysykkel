package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.klient;

import net.lovholm.oslobysykkel.integrasjon.oslobysykkel.modell.StationInformationFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StationInformationKlient {

    private final RestTemplate restTemplate;

    @Autowired
    public StationInformationKlient(@Qualifier("restTemplate") RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    StationInformationFeed getStationInformation(String stationInformationUrl ) {
        return restTemplate.getForObject(stationInformationUrl, StationInformationFeed.class);
    }
}
