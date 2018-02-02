package malenko.persistance.impl;

import malenko.model.Airline;
import malenko.persistance.AirlineInMemoryRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AirlineInMemoryRepositoryImpl extends AirlineInMemoryRepository {

    public AirlineInMemoryRepositoryImpl(Map<UUID, Airline> state) {
        super(state);
    }

    public boolean exists(String name) {
        return state.values()
                .stream()
                .anyMatch(airline -> airline.getName().equals(name));
    }

    public Optional<Airline> read(String name) {
        return state.values()
                .stream()
                .filter(airline -> airline.getName().equals(name))
                .findFirst();
    }
}
