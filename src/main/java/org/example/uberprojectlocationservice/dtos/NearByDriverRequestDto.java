package org.example.uberprojectlocationservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NearByDriverRequestDto {
    private Double longitude;
    private Double latitude;
}
