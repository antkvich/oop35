package by.bsuir.poit.transport.serialization;

import by.bsuir.poit.transport.exception.PropertiesReadingException;

import java.util.Optional;

public interface Serialization<T> {

    void serialize(T entity) throws PropertiesReadingException;

    Optional<T> deserialize() throws PropertiesReadingException;
}
