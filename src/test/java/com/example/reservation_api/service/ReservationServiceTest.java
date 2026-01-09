package com.example.reservation_api.service;

import com.example.reservation_api.entity.Reservation;
import com.example.reservation_api.entity.User;
import com.example.reservation_api.entity.Resource;
import com.example.reservation_api.repository.ReservationRepository;
import com.example.reservation_api.repository.UserRepository;
import com.example.reservation_api.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservationServiceTest {

    private ReservationService reservationService;
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private ResourceRepository resourceRepository;

    private User user;
    private Resource resource;

    @BeforeEach
    void setup() {
        reservationRepository = mock(ReservationRepository.class);
        userRepository = mock(UserRepository.class);
        resourceRepository = mock(ResourceRepository.class);

        reservationService = new ReservationService(reservationRepository, userRepository, resourceRepository);

        user = new User();
        user.setId(1L);
        user.setName("Fatima");
        user.setEmail("fatima@mail.com");

        resource = new Resource();
        resource.setId(1L);
        resource.setName("Meeting Room");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(resourceRepository.findById(1L)).thenReturn(java.util.Optional.of(resource));
    }

    @Test
    void testCreateReservationSuccess() {
        Reservation res = new Reservation();
        res.setUser(user);
        res.setResource(resource);
        res.setStartTime(LocalDateTime.of(2026,1,9,16,0));
        res.setEndTime(LocalDateTime.of(2026,1,9,17,0));

        when(reservationRepository.findByResourceAndStartTimeLessThanAndEndTimeGreaterThan(
                any(Resource.class), any(LocalDateTime.class), any(LocalDateTime.class)
        )).thenReturn(java.util.Collections.emptyList());

        when(reservationRepository.save(res)).thenReturn(res);

        Reservation created = reservationService.createReservation(res);
        assertEquals(res.getStartTime(), created.getStartTime());
        assertEquals(res.getUser().getName(), created.getUser().getName());
    }

    @Test
    void testCreateReservationConflict() {
        Reservation res = new Reservation();
        res.setUser(user);
        res.setResource(resource);
        res.setStartTime(LocalDateTime.of(2026,1,9,16,0));
        res.setEndTime(LocalDateTime.of(2026,1,9,17,0));

        when(reservationRepository.findByResourceAndStartTimeLessThanAndEndTimeGreaterThan(
                any(Resource.class), any(LocalDateTime.class), any(LocalDateTime.class)
        )).thenReturn(java.util.Collections.singletonList(res));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            reservationService.createReservation(res);
        });
        assertEquals("Resource already reserved", ex.getMessage());
    }

    @Test
    void testCreateReservationInvalidTime() {
        Reservation res = new Reservation();
        res.setUser(user);
        res.setResource(resource);
        res.setStartTime(LocalDateTime.of(2026,1,9,18,0));
        res.setEndTime(LocalDateTime.of(2026,1,9,17,0));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.createReservation(res);
        });
        assertEquals("Invalid time slot", ex.getMessage());
    }
}
