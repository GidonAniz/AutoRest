package com.autorest.autorest.web.controller;

import com.autorest.autorest.mapper.Mapper;
import com.autorest.autorest.service.AppService;
import com.autorest.autorest.web.dto.ClientDto;
import com.autorest.autorest.web.dto.VehicleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/autorest")
public class Controller {
    private final AppService appService;
    private final Mapper mapper;

    @GetMapping("/client_with_car")
    public ResponseEntity<List<ClientDto>> getAllClientsWithCar() {
        return ResponseEntity.ok(appService.getAllClientsWithCar());
    }

    @GetMapping("vehicles/available")
    public ResponseEntity<List<VehicleDto>> getAllFreeToRentCars() {
        return ResponseEntity.ok(appService.getAllFreeToRentVehicles());
    }

    @GetMapping("vehicles/belowPrice/{price}")
    public ResponseEntity<List<VehicleDto>> getAllCarsWithPriceLowerThan(@PathVariable BigDecimal price) {
        return ResponseEntity.ok(appService.getAllVehiclesWithPriceLowerThan(price));
    }

    @GetMapping("clients/late")
    public ResponseEntity<List<ClientDto>> getAllClientsWithDropOffTimeAfter() {
        return ResponseEntity.ok(appService.getAllClientsWithDropOffTimeAfter(LocalDateTime.now()));
    }

    @PostMapping("client/insert")
    public ResponseEntity<ClientDto> insert(@RequestBody ClientDto clientDto) {
        ClientDto persistClient = appService.insertClient(clientDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").build(persistClient.getUuid());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(persistClient);
    }
    @PostMapping("vehicle/insert")
    public ResponseEntity<VehicleDto> insert(@RequestBody VehicleDto vehicleDto) {
        VehicleDto persistVehicle = appService.insertVehicle(vehicleDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").build(persistVehicle.getUuid());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(persistVehicle);
    }

    @PostMapping("checkin/{clientUuid}/{vehicleUuid}")
    public ResponseEntity<ClientDto> checkIn(@PathVariable UUID clientUuid, @PathVariable UUID vehicleUuid) {
        return ResponseEntity.ok(appService.checkIn(clientUuid, vehicleUuid).get());
    }
}
