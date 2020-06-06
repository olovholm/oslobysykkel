package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.modell;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StationStatusFeed {

    @JsonProperty("data")
    private StationStatusData data;

    public StationStatusData getData() {
        return data;
    }

    public class StationStatusData {
        @JsonProperty("stations")
        private List<StationStatus> stations;

        public List<StationStatus> getStations() {
            return stations;
        }
    }


}
