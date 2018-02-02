package malenko.controller;

import malenko.model.Aircraft;
import malenko.model.AircraftModel;
import malenko.model.Airline;
import malenko.service.AirlineService;
import malenko.service.impl.AircraftFactory;
import malenko.service.impl.AirlineServiceImpl;
import malenko.service.impl.AirlineFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class AirlineController {

    private final AirlineService airlineService;
    private final Scanner sc;

    public AirlineController(AirlineService airlineService, Scanner sc) {
        this.airlineService = airlineService;
        this.sc = sc;
    }

    public void createAirline() {
        System.out.println("Enter a name of your airline company:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if (name.isEmpty()) {
            say("You must enter a name of your company!");
        } else {
            if (airlineService.exists(name)) {
                say(String.format("Airline with name '%s' already exists", name));
            } else {
                Airline created = airlineService.create(AirlineFactory.instance(name));
                say(String.format("Airline: %s was created.", created.getId()) );
            }
        }
    }

    public void addAircraftToAirline() {
        doWithAirline(airline -> {
            say("Please, enter model of available aircraft:");
            Stream.of(AircraftModel.values())
                    .forEach(model -> say(String.format(" - %s \n", model.name())));

            String aircraftName = sc.nextLine();
            boolean aircraftExists = Stream.of(AircraftModel.values())
                    .anyMatch(model -> model.name().equals(aircraftName));

            if (!aircraftExists) {
                say(String.format("Invalid aircraft name: '%s'", aircraftName));
                return;
            } else {
                AircraftModel model = AircraftModel.valueOf(aircraftName);
                Aircraft aircraft = AircraftFactory.instance(model);

                List<Aircraft> aircrafts = new ArrayList<>(airline.getAircrafts());
                aircrafts.add(aircraft);

                Airline newAirline = airline.copy(aircrafts);

                airlineService.update(airline.getId(), newAirline);
                say(String.format("Airline: %s was updated.", newAirline.getId()) );
            }
        });
    }

    public void printAirlines() {
        List<Airline> airlines = airlineService.list();
        say("All Airlines:");
        airlines.forEach(airline -> say(String.format(" - %s", airline.getName())));
    }

    public void calculateTotalCapacity() {
        doWithAirline(airline -> {
            int totalCapacity = airline.getAircrafts().stream()
                    .mapToInt(Aircraft::getCapacity)
                    .sum();
            say(String.format("Total capacity is of airline %s: %d", airline.getName(), totalCapacity));
        });
    }

    public void calculateCarryingCapacity() {
        doWithAirline(airline -> {
            int totalCarryingCapacity = airline.getAircrafts().stream()
                    .mapToInt(Aircraft::getCarryingCapacity)
                    .sum();
            say(String.format("Total carrying capacity is of airline %s: %d", airline.getName(), totalCarryingCapacity));
        });
    }

    public void displaySortedAircraftsList() {
        doWithAirline(airline -> {
            say(String.format("Aircrafts of airline: %s", airline.getName()));
            airline.getAircrafts().stream()
                    .sorted(Aircraft::compareTo)
                    .forEach(aircraft -> say(String.format(" - %s", aircraft.toString())));
        });
    }

    public void givenRangeCorresponding() {
        doWithAirline(airline -> {

            say("Please, enter start of range of fuel consumption");
            int start = sc.nextInt();
            say("Please, enter end of range of fuel consumption");
            int end = sc.nextInt();

            say(String.format("Aircrafts of airline %s with fuel consumption between %d and %d", airline.getName(), start, end));
            airline.getAircrafts().stream()
                    .filter(aircraft -> aircraft.getFuelConsumption() >= start && aircraft.getFuelConsumption() < end)
                    .forEach(aircraft -> say(String.format(" - %s", aircraft.toString())));
        });
    }

    private void say(String message) {
        System.out.println(message);
    }

    private void doWithAirline(Consumer<Airline> consumer) {
        List<Airline> airlines = airlineService.list();
        say("Please, enter name of available airline:");
        airlines.forEach(airline -> say(String.format(" - %s \n", airline.getName())));

        String airlineName = sc.nextLine();

        Optional<Airline> maybeAirline = airlineService.read(airlineName);

        if (!maybeAirline.isPresent()) {
            say(String.format("Invalid airline name: '%s'", airlineName));
            return;
        } else {
            Airline airline = maybeAirline.get();
            consumer.accept(airline);
        }
    }
}
