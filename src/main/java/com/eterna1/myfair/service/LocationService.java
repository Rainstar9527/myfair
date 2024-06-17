package com.eterna1.myfair.service;

import com.eterna1.myfair.vo.Location;

import java.util.List;

public interface LocationService {

    List<Location> selectAllLocations();

    List<Location> selectEmptyLocation();

    void updateLocation(Location location);
}
