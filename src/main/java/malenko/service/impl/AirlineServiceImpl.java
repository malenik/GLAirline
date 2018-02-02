package malenko.service.impl;

import malenko.model.Airline;
import malenko.persistance.AirlineInMemoryRepository;
import malenko.service.AirlineService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AirlineServiceImpl implements AirlineService {

    private final AirlineInMemoryRepository airlineRepo;

    public AirlineServiceImpl(AirlineInMemoryRepository airlineRepo) {
        this.airlineRepo = airlineRepo;
    }

    @Override
    public List<Airline> list() {
        return airlineRepo.list();
    }

    @Override
    public Optional<Airline> read(UUID id) {
        return airlineRepo.read(id);
    }

    @Override
    public Optional<Airline> read(String name) {
        return airlineRepo.read(name);
    }

    @Override
    public Boolean exists(UUID id) {
        return airlineRepo.exists(id);
    }

    public Boolean exists(String name) {
        return airlineRepo.exists(name);
    }

    @Override
    public Airline create(Airline airline) {
        return airlineRepo.create(airline);
    }

    @Override
    public Optional<Airline> update(UUID id, Airline airline) {
        return airlineRepo.update(id, airline);
    }

    @Override
    public Optional<Airline> delete(UUID id, Airline airline) {
        return airlineRepo.delete(id);
    }
}
