package pl.altkom.animalhotel.reservationservice.client.model;

import lombok.Data;

@Data
public class Owner {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
}
