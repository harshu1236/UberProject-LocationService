package org.example.uberprojectlocationservice.service;

import org.example.uberprojectlocationservice.dtos.DriverLocationDto;
import java.util.List;

public interface LocationService {
    Boolean saveDriverLocation(String driverId,Double latitude,Double longitude);

    List<DriverLocationDto> getNearByDriver(Double latitude,Double longitude);
}
