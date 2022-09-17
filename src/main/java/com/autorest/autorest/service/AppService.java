package com.autorest.autorest.service;

import com.autorest.autorest.web.dto.ClientDto;
import com.autorest.autorest.web.dto.VehicleDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public interface AppService {

    ClientDto insertClient(ClientDto clientDto);

    VehicleDto insertVehicle(VehicleDto vehicleDto);


    Optional<ClientDto> getClientByUuid(UUID clientUuid);

    List<ClientDto> getAllClientsWithCar();

    List<ClientDto> getAllClientsWithDropOffTimeAfter(LocalDateTime carDropOffTime);

    Optional<ClientDto> checkIn(UUID clientUuid, UUID vehicleUuid);

    Optional<VehicleDto> getVehicleByUuid(UUID VehicleUuid);

    List<VehicleDto> getAllFreeToRentVehicles();

    List<VehicleDto> getAllFreeToRentVehiclesByManufacture(String manufacture);

    List<VehicleDto> getAllVehiclesWithPriceLowerThan(BigDecimal price);

}
