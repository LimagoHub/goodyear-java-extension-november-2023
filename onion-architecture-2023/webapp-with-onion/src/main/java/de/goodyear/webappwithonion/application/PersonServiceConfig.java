package de.goodyear.webappwithonion.application;


import de.goodyear.repository.PersonRepository;
import de.goodyear.service.PersonenService;
import de.goodyear.service.inner.PersonenServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonServiceConfig {

    @Bean
    public PersonenService createPersonService(final PersonRepository repo){

        return new PersonenServiceImpl(repo);
    }
}
