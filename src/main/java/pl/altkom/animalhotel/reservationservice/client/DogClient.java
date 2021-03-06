package pl.altkom.animalhotel.reservationservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.altkom.animalhotel.reservationservice.client.model.Dog;

@FeignClient(value = "DOG-SERVICE")
public interface DogClient {

    @GetMapping("/dog/{dogId}")
    Dog getDog(@PathVariable("dogId") final Long dogId);

    @GetMapping("/dog")
    List<Dog> getDogs();
}
