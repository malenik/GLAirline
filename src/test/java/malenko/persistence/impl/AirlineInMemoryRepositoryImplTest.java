package malenko.persistence.impl;

import malenko.model.Airline;
import malenko.service.impl.AirlineFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class AirlineInMemoryRepositoryImplTest {
    @Test
    public void testCreate() {
        AirlineInMemoryRepositoryImpl repository = new AirlineInMemoryRepositoryImpl();

        Airline airline1 = AirlineFactory.instance("airline1");

        repository.create(airline1);

        Assert.assertTrue("Airline must exist by name", repository.exists("airline1"));
        Assert.assertTrue("Airline must exist by id", repository.exists(airline1.getId()));
        Assert.assertFalse("Airline must not exist by name", repository.exists("airline2"));
        Assert.assertFalse("Airline must not exist by id", repository.exists(UUID.randomUUID()));
    }
}