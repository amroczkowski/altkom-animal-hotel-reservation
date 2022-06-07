package pl.altkom.animalhotel.reservationservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import pl.altkom.animalhotel.reservationservice.client.DogClient;
import pl.altkom.animalhotel.reservationservice.client.OwnerClient;
import pl.altkom.animalhotel.reservationservice.controller.mapper.RequestMapper;
import pl.altkom.animalhotel.reservationservice.controller.mapper.ResponseMapper;
import pl.altkom.animalhotel.reservationservice.controller.model.CreateReservationRequest;
import pl.altkom.animalhotel.reservationservice.controller.model.Dog;
import pl.altkom.animalhotel.reservationservice.controller.model.Owner;
import pl.altkom.animalhotel.reservationservice.controller.model.Reservation;
import pl.altkom.animalhotel.reservationservice.controller.model.UpdateReservationRequest;
import pl.altkom.animalhotel.reservationservice.repository.ReservationRepository;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final OwnerClient ownerClient;
    private final DogClient dogClient;
    private final ReservationRepository reservationRepository;

    public List<Reservation> getReservations() {
        final List<Owner> owners = pl.altkom.animalhotel.reservationservice.client.mapper.ResponseMapper.mapOwners(ownerClient.getOwners());
        final List<Dog> dogs = pl.altkom.animalhotel.reservationservice.client.mapper.ResponseMapper.mapDogs(dogClient.getDogs());
        return ResponseMapper.map(reservationRepository.findAll(), owners, dogs);
    }

    public Reservation getReservation(final Long reservationId) {
        final pl.altkom.animalhotel.reservationservice.repository.model.Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final Owner owner = pl.altkom.animalhotel.reservationservice.client.mapper.ResponseMapper
                .map(ownerClient.getOwner(reservation.getOwnerId()));
        final Dog dog = pl.altkom.animalhotel.reservationservice.client.mapper.ResponseMapper
                .map(dogClient.getDog(reservation.getDogId()));

        return ResponseMapper.map(reservation, owner, dog);
    }

    public Reservation createReservation(final CreateReservationRequest request) {
        final pl.altkom.animalhotel.reservationservice.repository.model.Reservation savedReservation = reservationRepository
                .save(RequestMapper.bind(request));

        final Owner owner = pl.altkom.animalhotel.reservationservice.client.mapper.ResponseMapper
                .map(ownerClient.getOwner(savedReservation.getOwnerId()));
        final Dog dog = pl.altkom.animalhotel.reservationservice.client.mapper.ResponseMapper
                .map(dogClient.getDog(savedReservation.getDogId()));

        return ResponseMapper.map(savedReservation, owner, dog);
    }

    public Reservation updateReservation(final Long reservationId, final UpdateReservationRequest request) {

        final pl.altkom.animalhotel.reservationservice.repository.model.Reservation sourceReservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final pl.altkom.animalhotel.reservationservice.repository.model.Reservation modifiedReservation = reservationRepository
                .save(RequestMapper.bind(request, sourceReservation));
        final Owner owner = pl.altkom.animalhotel.reservationservice.client.mapper.ResponseMapper
                .map(ownerClient.getOwner(modifiedReservation.getOwnerId()));
        final Dog dog = pl.altkom.animalhotel.reservationservice.client.mapper.ResponseMapper
                .map(dogClient.getDog(modifiedReservation.getDogId()));

        return ResponseMapper.map(modifiedReservation, owner, dog);
    }
}
