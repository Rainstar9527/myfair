package com.eterna1.myfair.controller;

import com.eterna1.myfair.common.Result;
import com.eterna1.myfair.service.LocationService;
import com.eterna1.myfair.vo.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping("/getAllLocation")
    public Result selectAllLocations()
    {
        Result result = new Result();
        List<Location> locations = locationService.selectAllLocations();
        result.setData(locations);
        result.setFlag(true);
        result.setMessage("Success");
        return result;
    }
}
