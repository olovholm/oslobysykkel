package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.modell;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lang {

    @JsonProperty("feeds")
    private List<Feed> feeds;

    public List<Feed> getFeeds() {
        return feeds;
    }
}
