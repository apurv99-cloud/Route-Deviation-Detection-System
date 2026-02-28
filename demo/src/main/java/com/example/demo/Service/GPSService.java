package com.example.demo.Service;

import java.time.LocalDateTime;

import com.example.demo.Entity.GPS;
import com.example.demo.Entity.Trip;
import com.example.demo.Repository.GPSRepo;
import com.example.demo.Repository.TripRepo;
import org.springframework.stereotype.Service;

@Service
public class GPSService {

    private final TripRepo tripRepo;
    private final GPSRepo gpsRepo;
    private final DeviationService deviationService;

    public GPSService(
            TripRepo tripRepo,
            GPSRepo gpsRepo,
            DeviationService deviationService) {
        this.tripRepo = tripRepo;
        this.gpsRepo = gpsRepo;
        this.deviationService = deviationService;
    }

    public void processGps(Long tripId, Double lat, Double lon) {

        Trip trip = tripRepo.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        // Save GPS log
        GPS log = new GPS();
        log.setLatitude(lat);
        log.setLongitude(lon);
        log.setTimestamp(LocalDateTime.now());
        log.setTrip(trip);
        gpsRepo.save(log);

        // Optional: Only call ML occasionally or on trip completion
    }

    // Call this when trip ends
    public void completeTrip(Long tripId) {

        Trip trip = tripRepo.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        deviationService.checkTripWithML(trip);
    }
}