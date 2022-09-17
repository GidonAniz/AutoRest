package com.autorest.autorest.service;

import com.autorest.autorest.entity.Client;
import com.autorest.autorest.entity.Vehicle;
import com.autorest.autorest.mapper.Mapper;
import com.autorest.autorest.repository.ClientRepository;
import com.autorest.autorest.repository.VehicleRepository;
import com.autorest.autorest.service.ex.ClientOrVehicleNotFound;
import com.autorest.autorest.service.ex.ClientAlreadyExist;
import com.autorest.autorest.web.dto.ClientDto;
import com.autorest.autorest.web.dto.VehicleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;
    private final Mapper mapper;

    @Override
    public ClientDto insertClient(ClientDto dto) {
        UUID clientUuid = dto.getUuid();
        Optional<Client> optClient = clientRepository.findByUuid(clientUuid);

        if (optClient.isPresent()) {
            throw new ClientAlreadyExist(optClient.get().getName() + " already exist ");
        }
        return mapper.map(clientRepository.save(mapper.map(dto)));
    }

    @Override
    public VehicleDto insertVehicle(VehicleDto vehicleDto) {
        UUID uuid = vehicleDto.getUuid();
        Optional<Vehicle> optVehicle = vehicleRepository.findByUuid(uuid);

        if (optVehicle.isPresent()) {
            throw new ClientAlreadyExist(optVehicle.get().getType() + " already exist ");
        }
        return mapper.map(vehicleRepository.save(mapper.map(vehicleDto)));
    }

    @Override
    public Optional<ClientDto> getClientByUuid(UUID clientUuid) {
        return clientRepository.findByUuid(clientUuid).map(mapper::map);
    }

    @Override
    public List<ClientDto> getAllClientsWithCar() {
        return clientRepository
                .findAllByVehiclesIsNotNull().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public List<ClientDto> getAllClientsWithDropOffTimeAfter(LocalDateTime now) {
        return clientRepository
                .findAllByCarDropOffTimeAfter(now)
                .stream().map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDto> checkIn(UUID clientUuid, UUID vehicleUuid) {
        Optional<Client> clientByUuid = clientRepository.findByUuid(clientUuid);
        Optional<Vehicle> vehicleByUuid = vehicleRepository.findByUuid(vehicleUuid);
        if (clientByUuid.isEmpty() || vehicleByUuid.isEmpty()) {
            throw new ClientOrVehicleNotFound("client or vehicle not found");
        }
        clientByUuid.get().getVehicles().add(vehicleByUuid.get());
        vehicleByUuid.get().setClient(clientByUuid.get());
        vehicleByUuid.get().setFreeToRent(false);
        vehicleRepository.save(vehicleByUuid.get());
        return Optional.of(mapper.map(clientRepository.save(clientByUuid.get())));

    }

    @Override
    public Optional<VehicleDto> getVehicleByUuid(UUID vehicleUuid) {
        return vehicleRepository.findByUuid(vehicleUuid).map(mapper::map);
    }

    @Override
    public List<VehicleDto> getAllFreeToRentVehicles() {
        return toVehicleDtoList(vehicleRepository
                .findAllByFreeToRentIsTrue());
    }

    private List<VehicleDto> toVehicleDtoList(Set<Vehicle> vehicleRepository) {
        return vehicleRepository
                .stream().map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> getAllFreeToRentVehiclesByManufacture(String manufacture) {
        return toVehicleDtoList(vehicleRepository
                .findAllByManufacture(manufacture));
    }

    @Override
    public List<VehicleDto> getAllVehiclesWithPriceLowerThan(BigDecimal price) {
        return toVehicleDtoList(vehicleRepository
                .findAllByPriceIsLessThan(price));
    }
}
