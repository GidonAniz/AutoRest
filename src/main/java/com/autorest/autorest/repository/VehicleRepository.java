package com.autorest.autorest.repository;

import com.autorest.autorest.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.*;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByUuid(UUID uuid);

    Set<Vehicle> findAllByFreeToRentIsTrue();

    Set<Vehicle> findAllByManufacture(String manufacture);

    Set<Vehicle> findAllByPriceIsLessThan(BigDecimal price);

}
