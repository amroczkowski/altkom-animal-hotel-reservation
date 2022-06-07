package pl.altkom.animalhotel.reservationservice.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.altkom.animalhotel.reservationservice.controller.model.Dog;
import pl.altkom.animalhotel.reservationservice.controller.model.Owner;
import pl.altkom.animalhotel.reservationservice.repository.model.Reservation;

public class ResponseMapper {
    public static List<pl.altkom.animalhotel.reservationservice.controller.model.Reservation> map(final List<Reservation> reservations,
            final List<Owner> owners, final List<Dog> dogs) {
        return reservations.stream()
                .map(reservation -> map(reservation,
                        owners.stream().filter(owner -> owner.getId().equals(reservation.getOwnerId())).findFirst().orElseThrow(),
                        dogs.stream().filter(dog -> dog.getId().equals(reservation.getDogId())).findFirst().orElseThrow()))
                .collect(Collectors.toList());
    }

    public static pl.altkom.animalhotel.reservationservice.controller.model.Reservation map(final Reservation reservation,
            final Owner owner, final Dog dog) {
        return pl.altkom.animalhotel.reservationservice.controller.model.Reservation.builder()
                .id(reservation.getId())
                .startDate(reservation.getStartDate())
                .days(reservation.getDays())
                .dog(dog)
                .owner(owner)
                .build();
    }
}
