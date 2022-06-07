package pl.altkom.animalhotel.reservationservice.controller.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class UpdateReservationRequest {

    @NotNull
    private LocalDate startDate;
    @NotNull
    private Integer days;
    @NotNull
    private Long ownerId;
    @NotNull
    private Long dogId;
}
