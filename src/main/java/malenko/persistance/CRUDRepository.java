package malenko.persistance;

import malenko.model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<ID, T extends Identifiable<ID>> {

    List<T> list();

    Optional<T> read(ID id);

    boolean exists(ID id);

    T create(T t);

    Optional<T> update(ID id, T t);

    Optional<T> delete(ID id);

}
