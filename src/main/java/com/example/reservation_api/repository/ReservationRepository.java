package com.example.reservation_api.repository;


import com.example.reservation_api.entity.Reservation;
import com.example.reservation_api.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByResourceAndStartTimeLessThanAndEndTimeGreaterThan(
        Resource resource,
        LocalDateTime end,
        LocalDateTime start
    );
}

