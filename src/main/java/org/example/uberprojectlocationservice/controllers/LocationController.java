package org.example.uberprojectlocationservice.controllers;

import org.example.uberprojectlocationservice.dtos.DriverLocationDto;
import org.example.uberprojectlocationservice.dtos.NearByDriverRequestDto;
import org.example.uberprojectlocationservice.dtos.SaveDriverLocationDto;
import org.example.uberprojectlocationservice.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/drivers")
    public ResponseEntity<?> saveDriverLocation(@RequestBody SaveDriverLocationDto driverLocationDto) {
        try{
            Boolean response = locationService.saveDriverLocation(driverLocationDto.getDriverId(),driverLocationDto.getLatitude(),driverLocationDto.getLongitude());
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("false",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/nearby/drivers")
    public ResponseEntity<?> getNearbyDrivers(@RequestBody NearByDriverRequestDto nearByDriverRequestDto) {
        try{
            List<DriverLocationDto> drivers = locationService.getNearByDriver(nearByDriverRequestDto.getLatitude(),nearByDriverRequestDto.getLongitude());
            System.out.println(drivers);
            return new ResponseEntity<>(drivers,HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>("false",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
