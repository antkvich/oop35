package by.bsuir.poit.transport.storage;

import by.bsuir.poit.transport.model.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum TransportStorage {
    INSTANCE;

    private final List<Transport> storage;

    TransportStorage() {
        this.storage = new ArrayList<>();
    }

    public void add(Transport entity) {
        storage.add(entity);
    }

    public void addAll(List<Transport> entityList) {
        storage.addAll(entityList);
    }

    public void update(Transport entity) {
        Optional<Transport> transport = findById(entity.getId());
        if (transport.isPresent()) {
            storage.remove(transport.get());
            storage.add(entity);
        }
    }

    public List<Transport> findAll() {
        return new ArrayList<>(storage);
    }

    public void delete(Transport entity) {
        storage.remove(entity);
    }

    private Optional<Transport> findById(long id) {
        return storage.stream()
                .filter(transport -> transport.getId() == id)
                .findFirst();
    }
}
