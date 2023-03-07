package br.com.manageperson.config;

import br.com.manageperson.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
@RequiredArgsConstructor
public class LocalConfig {

    private final DBService dbService;

    @Bean
    public void instanciaDB() {
        this.dbService.instantiateDatabase();
    }
}
