package sopt.sopt36goodoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sopt36GoodocApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sopt36GoodocApplication.class, args);
    }

}
