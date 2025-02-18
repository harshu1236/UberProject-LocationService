package org.example.uberprojectlocationservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveDriverLocationDto {
    private String driverId;
    private Double longitude;
    private Double latitude;
}
