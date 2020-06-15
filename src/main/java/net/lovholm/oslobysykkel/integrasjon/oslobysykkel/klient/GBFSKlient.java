package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.klient;

import net.lovholm.oslobysykkel.integrasjon.oslobysykkel.gbfs_modell.GBFSFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GBFSKlient {
    private static final Logger log = LoggerFactory.getLogger(GBFSKlient.class);
    private final RestTemplate restTemplate;
    @Value("${oslobysykkel.gbfs.url}")
    private String GBFSRootUrl;

    @Autowired
    public GBFSKlient(@Qualifier("restTemplate") RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public GBFSFeed getGBFSFeed() {
        log.info("laster GBFS rot");
        return restTemplate.getForObject(GBFSRootUrl, GBFSFeed.class);
    }

}
