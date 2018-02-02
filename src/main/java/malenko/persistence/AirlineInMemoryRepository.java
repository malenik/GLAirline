package malenko.persistence;

import malenko.model.Airline;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

abstract public class AirlineInMemoryRepository extends CRUDRepositorySkeleton<UUID, Airline> {

    public AirlineInMemoryRepository(Map<UUID, Airline> state) {
        super(state);
    }

    public abstract boolean exists(String name);

    public abstract Optional<Airline> read(String name);
}
