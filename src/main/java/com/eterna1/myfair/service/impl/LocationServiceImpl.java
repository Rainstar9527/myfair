package com.eterna1.myfair.service.impl;

import com.eterna1.myfair.dao.LocationMapper;
import com.eterna1.myfair.service.LocationService;
import com.eterna1.myfair.vo.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationMapper locationMapper;

    @Override
    public List<Location> selectAllLocations() {
        return locationMapper.selectAllLocations();
    }
}
