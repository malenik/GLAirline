import malenko.controller.AirlineController;
import malenko.model.Airline;
import malenko.persistence.impl.AirlineInMemoryRepositoryImpl;
import malenko.service.AirlineService;
import malenko.service.impl.AirlineServiceImpl;

import java.text.ParseException;
import java.util.*;

public class AirlineLauncher {

    public static void main(String[] args) throws ParseException {
        console();
    }

    private static void console() throws ParseException {

        // Dependency injection to simplify testing
        Scanner sc = new Scanner(System.in);
        AirlineInMemoryRepositoryImpl repo = new AirlineInMemoryRepositoryImpl();
        AirlineService service = new AirlineServiceImpl(repo);
        AirlineController controller = new AirlineController(service, sc);

        while (true) {
            System.out.println(
                    "\n" +
                    "---AIRLINE SERVICE---\n" +
                            "Input next parameters:\n" +
                            "1. Create airline\n" +
                            "2. Add aircraft to an airline\n" +
                            "3. List all airlines\n" +
                            "4. Calculate total capacity of all the aircraft in the airline\n" +
                            "5. Calculate carrying capacity of all the aircraft in the airline\n" +
                            "6. Display the list of aircraft of the company sorted by flight range\n" +
                            "7. Find airplanes corresponding to a given range of fuel consumption parameters\n" +
                            "8. Exit" +
                    "\n"
            );

            String point = sc.nextLine();
            System.out.println();

            switch (point) {
                case "1":
                    controller.createAirline();
                    break;
                case "2":
                    controller.addAircraftToAirline();
                    break;
                case "3":
                    controller.printAirlines();
                    break;
                case "4":
                    controller.calculateTotalCapacity();
                    break;
                case "5":
                    controller.calculateCarryingCapacity();
                    break;
                case "6":
                    controller.displaySortedAircraftsList();
                    break;
                case "7":
                    controller.givenRangeCorresponding();
                    break;
                case "8":
                    exit();
                    break;
                default:
                    System.out.println("Wrong point. It should be between 1 and 9");
                    break;

            }

        }
    }

    private static void exit() {
        System.out.println("GOODBYE! APPLICATION IS CLOSED.");
        System.exit(0);
    }
}