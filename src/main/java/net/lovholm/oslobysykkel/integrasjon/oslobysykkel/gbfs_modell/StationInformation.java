package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.gbfs_modell;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationInformation {

    @JsonProperty("station_id")
    private String stationId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("short_name")
    private String shortName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lon")
    private String lon;
    @JsonProperty("region_id")
    private String regionId;
    @JsonProperty("capacity")
    private Integer capacity;

    public String getStationId() {
        return stationId;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getAddress() {
        return address;
    }

    public Double getLat() {
        return Double.parseDouble(lat);
    }

    public Double getLon() {
        return Double.parseDouble(lon);
    }

    public String getRegionId() {
        return regionId;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
