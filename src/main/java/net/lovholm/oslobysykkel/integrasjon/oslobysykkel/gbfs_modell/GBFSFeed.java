package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.gbfs_modell;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GBFSFeed {

    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("ttl")
    private Integer ttl;

    @JsonProperty("version")
    private String version;

    @JsonProperty("data")
    private GBFSData data;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public Integer getTtl() {
        return ttl;
    }

    public String getVersion() {
        return version;
    }

    public GBFSData getData() {
        return data;
    }
}
