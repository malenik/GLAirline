package malenko.service.impl;

import malenko.model.Aircraft;
import malenko.model.AircraftModel;

import java.util.UUID;

import static malenko.model.AircraftModel.*;

public class AircraftFactory {

    private AircraftFactory(){
    }

    public static Aircraft instance(AircraftModel model) {
        final UUID id = UUID.randomUUID();
        switch(model){
            case BOING_737:
                return new Aircraft(BOING_737, 168, 200, 5000, 20100, id);
            case BOING_767:
                return new Aircraft(BOING_767, 345, 370, 10450, 91380, id);
            case AIRBUS_A320:
                return new Aircraft(AIRBUS_A320, 180, 200, 6150, 30190, id);
            case AIRBUS_A330:
                return new Aircraft(AIRBUS_A330, 375, 400, 11300, 97530, id);
            default: throw new IllegalArgumentException("Wrong Plane type!");
        }
    }
}
