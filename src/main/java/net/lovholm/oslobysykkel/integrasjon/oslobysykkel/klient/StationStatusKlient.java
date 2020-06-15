package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.klient;

import net.lovholm.oslobysykkel.integrasjon.oslobysykkel.gbfs_modell.StationStatusFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StationStatusKlient {

    private final RestTemplate restTemplate;

    @Autowired
    public StationStatusKlient(@Qualifier("restTemplate") RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    StationStatusFeed getStationStatus(String stationInformationUrl ) {
        return restTemplate.getForObject(stationInformationUrl, StationStatusFeed.class);
    }
}
