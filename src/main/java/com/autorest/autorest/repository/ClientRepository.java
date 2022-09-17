package com.autorest.autorest.repository;

import com.autorest.autorest.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.*;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUuid(UUID uuid);

    Set<Client> findAllByVehiclesIsNotNull();

    Set<Client> findAllByCarDropOffTimeAfter(LocalDateTime dropOffTime);


}
