package com.example.demo.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Service.GPSService;

public class GPSController {
     private final GPSService gpsService;

    public GPSController(GPSService gpsService) {
        this.gpsService = gpsService;
    }

    @PostMapping("/{tripId}")
    public String receiveGps(
            @PathVariable Long tripId,
            @RequestParam Double lat,
            @RequestParam Double lon) {

        gpsService.processGps(tripId, lat, lon);

        return "GPS logged";
    }
    
}
