package malenko.persistence;

import malenko.model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<ID, T extends Identifiable<ID>> {

    List<T> list();

    Optional<T> read(ID id);

    boolean exists(ID id);

    T create(T t);

    /**
     * @param id identifier
     * @param t object to update
     * @return optional of previous value or empty if was not updated
     */
    Optional<T> update(ID id, T t);

    Optional<T> delete(ID id);

}
