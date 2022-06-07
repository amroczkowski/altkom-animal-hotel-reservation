package pl.altkom.animalhotel.reservationservice.client.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.altkom.animalhotel.reservationservice.controller.model.Dog;
import pl.altkom.animalhotel.reservationservice.controller.model.Owner;

public class ResponseMapper {

    public static List<Owner> mapOwners(final List<pl.altkom.animalhotel.reservationservice.client.model.Owner> owners) {
        return owners.stream()
                .map(ResponseMapper::map)
                .collect(Collectors.toList());
    }

    public static List<Dog> mapDogs(final List<pl.altkom.animalhotel.reservationservice.client.model.Dog> dogs) {
        return dogs.stream()
                .map(ResponseMapper::map)
                .collect(Collectors.toList());
    }

    public static Owner map(final pl.altkom.animalhotel.reservationservice.client.model.Owner owner) {
        return Owner.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .phone(owner.getPhone())
                .build();
    }

    public static Dog map(final pl.altkom.animalhotel.reservationservice.client.model.Dog dog) {
        return Dog.builder()
                .id(dog.getId())
                .name(dog.getName())
                .dateOfBirth(dog.getDateOfBirth())
                .breed(dog.getBreed())
                .ownerId(dog.getOwnerId())
                .build();
    }
}
