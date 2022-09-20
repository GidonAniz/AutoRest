package com.autorest.autorest.repository;

import com.autorest.autorest.entity.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void whenClientIsFetched_ShouldGetNonEmptyOptional() {
        UUID randomUuid = UUID.randomUUID();

        Client client = Client.builder()
                .uuid(randomUuid)
                .name("Dany")
                .creationTimestamp(Timestamp.from(Instant.now()))
                .build();

        clientRepository.save(client);
        Optional<Client> optProduct = clientRepository.findByUuid(randomUuid);

        Assertions.assertThat(optProduct)
                .isNotEmpty();
    }

    @Test
    public void whenClientIsFetched_ShouldGetOptionalClientWithSameUuid() {
        UUID randomUuid = UUID.randomUUID();

        Client client = Client.builder()
                .uuid(randomUuid)
                .name("Dany")
                .creationTimestamp(Timestamp.from(Instant.now()))
                .build();

        Client savedClient = clientRepository.save(client);
        Optional<Client> optClient = clientRepository.findByUuid(randomUuid);

        Assertions.assertThat(optClient)
                .get()
                .extracting(Client::getUuid)
                .isEqualTo(savedClient.getUuid());
    }
}