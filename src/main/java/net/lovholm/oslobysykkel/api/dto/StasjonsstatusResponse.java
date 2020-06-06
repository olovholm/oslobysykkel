package net.lovholm.oslobysykkel.api.dto;

import java.util.List;

public class StasjonsstatusResponse {

        List<StasjonstatusDto> stasjoner;

        public List<StasjonstatusDto> getStasjoner() {
                return stasjoner;
        }

        public void setStasjoner(List<StasjonstatusDto> stasjoner) {
                this.stasjoner = stasjoner;
        }
}
