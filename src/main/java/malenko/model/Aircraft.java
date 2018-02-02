package malenko.model;

import java.util.Objects;
import java.util.UUID;

public class Aircraft implements
        Transport,
        Refuelable,
        Identifiable<UUID>,
        Comparable<Aircraft> {

    private final AircraftModel model;
    private final int capacity;
    private final int carryingCapacity;
    private final int range;
    private final int fuelConsumption;
    private final UUID id;

    public Aircraft(
            AircraftModel model,
            int capacity,
            int carryingCapacity,
            int range,
            int fuelConsumption,
            UUID id
    ) {
        boolean isValidInput = isValidateInput(
                model,
                capacity,
                carryingCapacity,
                range,
                fuelConsumption,
                id
        );

        if (!isValidInput) {
            throw new IllegalArgumentException("Illegal input data");
        }

        this.model = model;
        this.capacity = capacity;
        this.carryingCapacity = carryingCapacity;
        this.range = range;
        this.fuelConsumption = fuelConsumption;
        this.id = id;
    }

    private boolean isValidateInput(
            AircraftModel model,
            int capacity,
            int carryingCapacity,
            int range,
            int fuelConsumption,
            UUID id
    ) {
        return model != null &&
                id != null &&
                capacity >= 0 &&
                carryingCapacity >= 0 &&
                range >= 0 &&
                fuelConsumption >= 0;
    }

    public AircraftModel getModel() {
        return model;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aircraft)) return false;
        Aircraft aircraft = (Aircraft) o;
        return capacity == aircraft.capacity &&
                carryingCapacity == aircraft.carryingCapacity &&
                range == aircraft.range &&
                fuelConsumption == aircraft.fuelConsumption &&
                model == aircraft.model &&
                Objects.equals(id, aircraft.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, capacity, carryingCapacity, range, fuelConsumption, id);
    }

    @Override
    public int compareTo(Aircraft that) {
        return Integer.compare(this.getRange(), that.getRange());
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "model=" + model +
                ", capacity=" + capacity +
                ", carryingCapacity=" + carryingCapacity +
                ", range=" + range +
                ", fuelConsumption=" + fuelConsumption +
                ", id=" + id +
                '}';
    }
}
