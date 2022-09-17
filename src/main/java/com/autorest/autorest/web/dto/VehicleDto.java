package com.autorest.autorest.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    @JsonProperty("id")
    private UUID uuid;
    private String manufacture;
    private String type;
    private boolean freeToRent;
    private BigDecimal price;
    private long totalKilometer;
}
