package com.autorest.autorest.mapper;

import com.autorest.autorest.entity.Client;
import com.autorest.autorest.entity.Vehicle;
import com.autorest.autorest.web.dto.ClientDto;
import com.autorest.autorest.web.dto.VehicleDto;
@org.mapstruct.Mapper
public interface Mapper {

    VehicleDto map(Vehicle vehicle);

    Vehicle map(VehicleDto vehicleDto);

    ClientDto map(Client client);

    Client map(ClientDto clientDto);

}
