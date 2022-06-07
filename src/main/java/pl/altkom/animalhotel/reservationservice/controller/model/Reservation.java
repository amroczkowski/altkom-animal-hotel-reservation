package pl.altkom.animalhotel.reservationservice.controller.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Reservation {

    private Long id;

    private LocalDate startDate;
    private Integer days;
    private Owner owner;
    private Dog dog;
}
