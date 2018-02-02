package malenko.persistance;

import malenko.model.Identifiable;

import java.util.*;

abstract public class CRUDRepositorySkeleton<ID, T extends Identifiable<ID>>
        implements CRUDRepository<ID, T> {

    protected Map<ID, T> state;

    public CRUDRepositorySkeleton(Map<ID, T> state) {
        this.state = state;
    }

    @Override
    public List<T> list() {
        return new ArrayList<>(state.values());
    }

    @Override
    public Optional<T> read(ID id) {
        return Optional.ofNullable(state.get(id));
    }

    @Override
    public boolean exists(ID id) {
        return Optional.ofNullable(state.get(id)).isPresent();
    }

    @Override
    public T create(T t) {
        Map<ID, T> newState = new HashMap<>(state);
        newState.put(t.getId(), t);
        this.state = newState;
        return t;
    }

    @Override
    public Optional<T> update(ID id, T t) {
        Optional<T> maybeUpdatedValue = Optional.ofNullable(state.get(id))
                .map(value -> {
                    Map<ID, T> newState = new HashMap<>(state);
                    newState.put(id, t);
                    this.state = newState;

                    return t;
                });
        return maybeUpdatedValue;
    }

    @Override
    public Optional<T> delete(ID id) {
        Optional<T> maybeDeletedValue = Optional.ofNullable(state.get(id))
                .map(value -> {
                    Map<ID, T> newState = new HashMap<>(state);
                    newState.remove(id);
                    this.state = newState;

                    return value;
                });
        return maybeDeletedValue;
    }

}
