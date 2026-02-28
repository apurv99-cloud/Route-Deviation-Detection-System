package com.example.demo.Controller;

import com.example.demo.Entity.Trip;
import com.example.demo.Repository.TripRepo;
import com.example.demo.Service.MlService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ml-test")
public class MLController {

    private final MlService mlService;
    private final TripRepo tripRepo;

    public MLController(MlService mlService, TripRepo tripRepo) {
        this.mlService = mlService;
        this.tripRepo = tripRepo;
    }

    @GetMapping("/{tripId}")
    public String testML(@PathVariable Long tripId) {

        Trip trip = tripRepo.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        boolean result = mlService.isAnomalous(
                trip.getOriginLat(),
                trip.getOriginLon(),
                trip.getDestLat(),
                trip.getDestLon(),
                trip.getDurationS(),
                trip.getPolyline());

        return "ML says anomaly: " + result;
    }
}