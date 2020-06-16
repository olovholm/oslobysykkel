package net.lovholm.oslobysykkel.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import net.lovholm.oslobysykkel.api.dto.StasjonsstatusResponse;
import net.lovholm.oslobysykkel.api.dto.StasjonstatusDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"oslobysykkel.gbfs.url = http://localhost:10000/gbfs.json"})
class OslobysykkelApplicationTests {
    static WireMockServer mockHttpServer = new WireMockServer(10000); // endpoint port here

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setup() throws Exception {
        mockHttpServer.stubFor(get(urlPathMatching("/gbfs.json"))
                .willReturn(
                        aResponse()
                                .withBodyFile("gbfs_root.json")
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json;charset=UTF-8")
                ));
        mockHttpServer.stubFor(get(urlPathMatching("/station_information.json"))
                .willReturn(
                        aResponse()
                                .withBodyFile("station_information.json")
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json;charset=UTF-8")
                ));
        mockHttpServer.stubFor(get(urlPathMatching("/station_status.json"))
                .willReturn(
                        aResponse()
                                .withBodyFile("station_status.json")
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json;charset=UTF-8")
                ));
        mockHttpServer.start();
    }


    @Test
    public void korrektStasjonsstatusSkalTilgjengeliggjøresBasertPåKilde() throws Exception {
        Thread.sleep(100);
        var stasjonsstatusResponse = this.restTemplate.getForObject("http://localhost:" + port + "/api/stasjonstatus/",
                StasjonsstatusResponse.class);

        assertThat("Stasjon 002 forventes å ha 666 plasser", finnStasjonBasertPåId(stasjonsstatusResponse,"002").getKapasitet() == 666);
        assertThat("Stasjon 002 foventes å ha 333 ledige sykler", finnStasjonBasertPåId(stasjonsstatusResponse,"002").getAntallLedigeSykler() == 333);
        assertThat("Stasjon 010 har navn Drammensveien", finnStasjonBasertPåId(stasjonsstatusResponse,"010").getNavn().equalsIgnoreCase("Drammensveien"));
    }


    @Test
    void contextLoads() {
    }


    private StasjonstatusDto finnStasjonBasertPåId(StasjonsstatusResponse stasjonsstatusResponse, String stasjonsId){
        return stasjonsstatusResponse.getStasjoner().stream().filter(it -> it.getStasjonsId().equalsIgnoreCase(stasjonsId)).findAny().get();
    }

}
