package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.gbfs_modell;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feed {
        @JsonProperty("name")
        private String name;
        @JsonProperty("url")
        private String url;

        public String getName() {
                return name;
        }

        public String getUrl() {
                return url;
        }
}
