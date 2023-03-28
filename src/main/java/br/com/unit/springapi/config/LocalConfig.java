package br.com.unit.springapi.config;

import br.com.unit.springapi.domain.User;
import br.com.unit.springapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB() {
        User usr1 = new User(null, "Joao", "joao@gmail.com", "3425");
        User usr2 = new User(null, "Maria", "maria@gmail.com", "3425");

        repository.saveAll(List.of(usr1, usr2));
    }
}
