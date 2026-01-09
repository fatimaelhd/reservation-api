package com.example.reservation_api.service;

import com.example.reservation_api.entity.Reservation;
import com.example.reservation_api.entity.User;
import com.example.reservation_api.entity.Resource;
import com.example.reservation_api.repository.ReservationRepository;
import com.example.reservation_api.repository.UserRepository;
import com.example.reservation_api.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            UserRepository userRepository,
            ResourceRepository resourceRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {

        //  Charger USER depuis la BD
        User user = userRepository.findById(reservation.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  Charger RESOURCE depuis la BD
        Resource resource = resourceRepository.findById(reservation.getResource().getId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        reservation.setUser(user);
        reservation.setResource(resource);

        //  Vérification des dates
        if (reservation.getStartTime().isAfter(reservation.getEndTime())) {
            throw new IllegalArgumentException("Invalid time slot");
        }

        //  Vérification des conflits
        List<Reservation> conflicts =
                reservationRepository.findByResourceAndStartTimeLessThanAndEndTimeGreaterThan(
                        resource,
                        reservation.getEndTime(),
                        reservation.getStartTime()
                );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Resource already reserved");
        }

        return reservationRepository.save(reservation);
    }
}
