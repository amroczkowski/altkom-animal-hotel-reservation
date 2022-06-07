package pl.altkom.animalhotel.reservationservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.altkom.animalhotel.reservationservice.controller.model.CreateReservationRequest;
import pl.altkom.animalhotel.reservationservice.controller.model.Reservation;
import pl.altkom.animalhotel.reservationservice.controller.model.UpdateReservationRequest;
import pl.altkom.animalhotel.reservationservice.service.ReservationService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reservation")
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public List<Reservation> getReservations() {
        log.info("Get all reservations");
        return reservationService.getReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") final Long reservationId) {
        log.info("Get reservation {}", reservationId);
        return reservationService.getReservation(reservationId);
    }

    @PostMapping
    public Reservation createReservation(@Valid @RequestBody final CreateReservationRequest request) {
        return reservationService.createReservation(request);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id") final Long reservationId, @Valid @RequestBody final UpdateReservationRequest request) {
        return reservationService.updateReservation(reservationId, request);
    }
}
