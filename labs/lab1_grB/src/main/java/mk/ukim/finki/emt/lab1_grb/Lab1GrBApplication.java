package mk.ukim.finki.emt.lab1_grb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Lab1GrBApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1GrBApplication.class, args);
    }

}
