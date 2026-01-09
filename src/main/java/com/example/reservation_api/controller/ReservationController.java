package com.example.reservation_api.controller;


import com.example.reservation_api.entity.Reservation;
import com.example.reservation_api.repository.ResourceRepository;
import com.example.reservation_api.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationService service;
    private final ResourceRepository resourceRepository;

    public ReservationController(ReservationService service,
                                 ResourceRepository resourceRepository) {
        this.service = service;
        this.resourceRepository = resourceRepository;
    }


    @PostMapping("/reservations")
    public Reservation reserve(@RequestBody Reservation reservation) {
        return service.createReservation(reservation);
    }
}

