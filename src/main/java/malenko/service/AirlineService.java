package malenko.service;

import malenko.model.Airline;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AirlineService {

    List<Airline> list();

    Optional<Airline> read(UUID id);

    Optional<Airline> read(String name);

    Boolean exists(UUID id);

    Boolean exists(String name);

    Airline create(Airline airline);

    Optional<Airline> update(UUID id, Airline airline);

    Optional<Airline> delete(UUID id, Airline airline);

}
