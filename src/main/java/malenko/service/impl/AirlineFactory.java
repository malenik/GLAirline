package malenko.service.impl;

import malenko.model.Airline;

import java.util.ArrayList;
import java.util.UUID;

public class AirlineFactory {

    private AirlineFactory(){
    }

    public static Airline instance(String name) {
        return new Airline(name, new ArrayList<>(), UUID.randomUUID());
    }
}
