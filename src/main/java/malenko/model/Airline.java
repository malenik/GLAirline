package malenko.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Airline implements Identifiable<UUID> {

    private final String name;
    private final List<Aircraft> aircrafts;
    private final UUID id;

    public Airline(String name, List<Aircraft> aircrafts, UUID id) {
        final boolean isValidInput = isValidInput(name, aircrafts, id);

        if (!isValidInput) {
            throw new IllegalArgumentException("Wrong input data");
        }

        this.name = name;
        this.aircrafts = aircrafts;
        this.id = id;
    }

    private boolean isValidInput(String name, List<Aircraft> aircrafts, UUID id) {
        return name != null && aircrafts != null && id != null;
    }

    public String getName() { return name; }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public UUID getId() {
        return id;
    }

    public Airline copy(List<Aircraft> aircrafts) {
        return new Airline(
                this.getName(),
                aircrafts,
                this.getId()
        );
    }

    @Override
    public String toString() {
        return "Airline{" +
                "name='" + name + '\'' +
                ", aircrafts=" + aircrafts +
                '}';
    }
}
