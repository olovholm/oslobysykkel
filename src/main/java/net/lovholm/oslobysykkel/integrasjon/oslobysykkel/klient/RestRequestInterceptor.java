package net.lovholm.oslobysykkel.integrasjon.oslobysykkel.klient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestRequestInterceptor implements ClientHttpRequestInterceptor {
    private final static Logger log = LoggerFactory.getLogger(RestRequestInterceptor.class);

    @Value("${oslobysykkel.client_identifier}")
    private String clientIdentifier;


    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        request.getHeaders().add("Client-Identifier",clientIdentifier);
        ClientHttpResponse response = execution.execute(request, body);

        var statusCode = response.getStatusCode();

        if(statusCode.is4xxClientError() || statusCode.is5xxServerError()){
            log.warn("Request feilet: {} med statuskode: {} og tekst: {} ", request.getURI().toString(), response.getStatusCode(), response.getStatusText());
        }

        return response;
    }









}
