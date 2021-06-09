package by.bsuir.poit.transport.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T> {

    void addToStorage(T entity);

    void addAllToStorage(List<T> entityList);

    List<T> findAll();

    void update(T entity);

    void delete(long entityId);

    Optional<T> findById(long id);
}
