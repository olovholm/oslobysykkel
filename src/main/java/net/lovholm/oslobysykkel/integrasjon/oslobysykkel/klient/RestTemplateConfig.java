package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.klient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class RestTemplateConfig {

    private static final int MILLIS_5_SEKUNDER = 5_000;
    private final RestRequestInterceptor restRequestInterceptor;


    public RestTemplateConfig(@Autowired RestRequestInterceptor restRequestInterceptor){
        this.restRequestInterceptor = restRequestInterceptor;
    }

    @Bean(name="restTemplate")
    @Primary
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){

        return restTemplateBuilder.requestFactory(SimpleClientHttpRequestFactory.class)
                .interceptors(restRequestInterceptor)
                .messageConverters(new MappingJackson2HttpMessageConverter())
                .setConnectTimeout(Duration.ofMillis(MILLIS_5_SEKUNDER))
                .build();

    }
}
