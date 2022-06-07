package pl.altkom.animalhotel.reservationservice.controller.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Owner {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
}