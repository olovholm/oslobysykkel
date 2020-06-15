package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.gbfs_modell;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StationInformationFeed {

    @JsonProperty("data")
    private StationInformationData data;

    public StationInformationData getData() {
        return data;
    }

    public class StationInformationData {
        @JsonProperty("stations")
        private List<StationInformation> stations;

        public List<StationInformation> getStations() {
            return stations;
        }
    }
}
