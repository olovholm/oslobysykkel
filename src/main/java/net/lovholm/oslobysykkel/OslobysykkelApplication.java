package net.lovholm.oslobysykkel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OslobysykkelApplication {

    public static void main(String[] args) {
        SpringApplication.run(OslobysykkelApplication.class, args);
    }

}
