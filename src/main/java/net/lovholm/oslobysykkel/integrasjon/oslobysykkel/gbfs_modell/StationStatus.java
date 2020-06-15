package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.gbfs_modell;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationStatus {
    @JsonProperty("station_id")
    private String stationId;
    @JsonProperty("num_bikes_available")
    private Integer numBikesAvailable;
    @JsonProperty("num_bikes_disabled")
    private Integer numBikesDisabled;
    @JsonProperty("num_docks_available")
    private Integer numDocksAvailable;
    @JsonProperty("num_docks_disabled")
    private Integer numDocksDisabled;
    @JsonProperty("is_installed")
    private boolean installed;
    @JsonProperty("is_renting")
    private boolean renting;
    @JsonProperty("is_returning")
    private boolean returning;
    @JsonProperty("last_reported")
    private Long lastReported;

    public String getStationId() {
        return stationId;
    }

    public Integer getNumBikesAvailable() {
        return numBikesAvailable;
    }

    public Integer getNumBikesDisabled() {
        return numBikesDisabled;
    }

    public Integer getNumDocksAvailable() {
        return numDocksAvailable;
    }

    public Integer getNumDocksDisabled() {
        return numDocksDisabled;
    }

    public boolean isInstalled() {
        return installed;
    }

    public boolean isRenting() {
        return renting;
    }

    public boolean isReturning() {
        return returning;
    }

    public Long getLastReported() {
        return lastReported;
    }
}
