package by.bsuir.wtl3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class SpringBootWebJspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebJspApplication.class, args);
    }

}
