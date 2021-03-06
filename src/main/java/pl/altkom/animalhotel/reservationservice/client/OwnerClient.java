package pl.altkom.animalhotel.reservationservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.altkom.animalhotel.reservationservice.client.model.Owner;

@FeignClient(value = "OWNER-SERVICE")
public interface OwnerClient {

    @GetMapping("/owner/{ownerId}")
    Owner getOwner(@PathVariable("ownerId") final Long ownerId);

    @GetMapping("/owner")
    List<Owner> getOwners();
}
