package by.bsuir.poit.transport.factory;

import by.bsuir.poit.transport.model.Transport;

public interface TransportFactory<T extends Transport> {

    T create(Object... args);
}
