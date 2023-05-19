package com.client.clientConnectivity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository){
        return args -> { Client mary = new Client("Mary", "mary@gmail.com");
                        Client marian = new Client("Marian", "marian@gmail.com");
                         clientRepository.saveAll(List.of(mary, marian));
            ;};
    }
}
