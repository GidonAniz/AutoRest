package com.autorest.autorest.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    @JsonProperty("id")
    private UUID uuid;
    private String name;
    private  List<VehicleDto> vehiclesDto = Collections.emptyList();
}
