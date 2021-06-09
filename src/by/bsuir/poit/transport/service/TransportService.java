package by.bsuir.poit.transport.service;

import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.storage.TransportStorage;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public enum TransportService implements CommonService<Transport> {
    INSTANCE;

    private final TransportStorage storage = TransportStorage.INSTANCE;

    @Override
    public void addToStorage(Transport entity) {
        storage.add(entity);
    }

    @Override
    public void addAllToStorage(List<Transport> entityList) {
        storage.addAll(entityList);
    }

    @Override
    public List<Transport> findAll() {
        return storage.findAll();
    }

    @Override
    public void update(Transport entity) {
        storage.update(entity);
    }

    @Override
    public void delete(long entityId) {
        Optional<Transport> transportOptional = findById(entityId);
        transportOptional.ifPresent(storage::delete);
    }

    @Override
    public Optional<Transport> findById(long id) {
        return findAll().stream()
                .filter(Objects::nonNull)
                .filter(transport -> transport.getId() == id)
                .findFirst();
    }
}
