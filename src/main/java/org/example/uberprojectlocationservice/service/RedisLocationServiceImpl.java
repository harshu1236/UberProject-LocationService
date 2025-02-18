package org.example.uberprojectlocationservice.service;

import org.example.uberprojectlocationservice.dtos.DriverLocationDto;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;

@Service
public class RedisLocationServiceImpl implements LocationService {

    private StringRedisTemplate stringRedisTemplate;

    private static final String DRIVER_GEO_POS_KEY = "drivers";

    private static final Double SEARCH_RADIUS = 5.0;

    public RedisLocationServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Boolean saveDriverLocation(String driverId, Double latitude, Double longitude) {
        GeoOperations<String,String> geoOperations = stringRedisTemplate.opsForGeo();
        geoOperations.add(DRIVER_GEO_POS_KEY,
                new RedisGeoCommands.GeoLocation<>(driverId,
                        new Point(latitude, longitude)));
        return true;
    }

    @Override
    public List<DriverLocationDto> getNearByDriver(Double latitude, Double longitude) {

        GeoOperations<String,String> geoOperations = stringRedisTemplate.opsForGeo();

        Distance distanceRadius = new Distance(SEARCH_RADIUS, Metrics.KILOMETERS);
        Circle circleWithin = new Circle(new Point(latitude, longitude), distanceRadius);

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOperations.radius(DRIVER_GEO_POS_KEY, circleWithin);

        List<DriverLocationDto> drivers = new ArrayList<>();

        for(GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult : results) {

            Point point = geoOperations.position(DRIVER_GEO_POS_KEY,geoResult.getContent().getName()).get(0);
            DriverLocationDto driverLocationDto = DriverLocationDto.builder()
                    .driverId(geoResult.getContent().getName())
                    .latitude(point.getX())
                    .longitude(point.getY())
                    .build();
            drivers.add(driverLocationDto);
        }
        return drivers;
    }
}
