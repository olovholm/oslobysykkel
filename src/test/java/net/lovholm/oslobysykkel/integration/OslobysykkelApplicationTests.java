package net.lovholm.oslobysykkel.integration;

import net.lovholm.oslobysykkel.api.dto.StasjonsstatusResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class OslobysykkelApplicationTests {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    //TODO: Smartify
   @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
       Thread.sleep(100);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/stasjonstatus/",
                StasjonsstatusResponse.class).getStasjoner().size() == 20 );
    }



    @Test
    void contextLoads() {
    }

}
