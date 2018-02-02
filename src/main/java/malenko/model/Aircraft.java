package malenko.model;

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
        if (model == null || id == null) {
            return false;
        } else if (capacity < 0 || carryingCapacity < 0|| range < 0 || fuelConsumption < 0) {
            return false;
        } else {
            return true;
        }
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
        if (o == null || getClass() != o.getClass()) return false;

        Aircraft aircraft = (Aircraft) o;

        if (capacity != aircraft.capacity) return false;
        if (carryingCapacity != aircraft.carryingCapacity) return false;
        if (range != aircraft.range) return false;
        if (fuelConsumption != aircraft.fuelConsumption) return false;
        if (model != aircraft.model) return false;
        return id != null ? id.equals(aircraft.id) : aircraft.id == null;
    }

    @Override
    public int hashCode() {
        int result = model != null ? model.hashCode() : 0;
        result = 31 * result + capacity;
        result = 31 * result + carryingCapacity;
        result = 31 * result + range;
        result = 31 * result + fuelConsumption;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
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
