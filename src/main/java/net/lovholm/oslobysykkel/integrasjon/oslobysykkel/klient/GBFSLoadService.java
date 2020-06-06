package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.klient;


import net.lovholm.oslobysykkel.integrasjon.oslobysykkel.modell.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GBFSLoadService {
    private static final Logger log = LoggerFactory.getLogger(GBFSKlient.class);


    private final GBFSKlient gbfsKlient;
    private final StationInformationKlient stationInformationKlient;
    private final StationStatusKlient stationStatusKlient;

    private String stationStatusUrl;
    private String stationInformationUrl;

    @Autowired
    public GBFSLoadService(GBFSKlient gbfsKlient, StationInformationKlient stationInformationKlient, StationStatusKlient stationStatusKlient) {
        this.gbfsKlient = gbfsKlient;
        this.stationInformationKlient = stationInformationKlient;
        this.stationStatusKlient = stationStatusKlient;
        lastPåkrevdeGBFSEndepunkter();
    }

    public List<StationInformation> getStationInformation() {
        StationInformationFeed stationInformation = stationInformationKlient.getStationInformation(stationInformationUrl);
        return Optional.of(stationInformation.getData().getStations()).get();
    }


    public List<StationStatus> getStationStatus() {
        StationStatusFeed stationStatus = stationStatusKlient.getStationStatus(stationStatusUrl);
        return Optional.of(stationStatus.getData().getStations()).get();
    }


    private void lastPåkrevdeGBFSEndepunkter() {
        try {
            GBFSFeed feeds = gbfsKlient.getGBFSFeed();
            List<Feed> GBFSFeeds = Optional
                    .of(feeds.getData().getNb().getFeeds())
                    .orElseThrow(() -> new IllegalStateException("Kunne ikke hente endepunkter for GBFS-tjenester"));


            this.stationStatusUrl = GBFSFeeds.stream().filter(
                    it -> it.getName().equalsIgnoreCase("station_status"))
                    .findAny().orElseThrow(() -> new IllegalStateException("Kunne ikke finne URL for statsjonsstatus")).getUrl();
            this.stationInformationUrl = GBFSFeeds.stream().filter(
                    it -> it.getName().equalsIgnoreCase("station_information"))
                    .findAny().orElseThrow(() -> new IllegalStateException("Kunne ikke finne URL for stasjonsinformasjon")).getUrl();
        } catch (IllegalStateException ex) {
            log.error("Kunne ikke laste nødvendige endepunkter for applikasjonen. Terminerer...");
            ex.printStackTrace();
            System.exit(-1);
        }
    }

}
