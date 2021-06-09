package by.bsuir.poit.transport.factory.impl;

import by.bsuir.poit.transport.factory.IdDispenser;
import by.bsuir.poit.transport.factory.TransportFactory;
import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.model.impl.Plane;
import by.bsuir.poit.transport.service.CommonService;
import by.bsuir.poit.transport.service.TransportService;

public enum PlaneFactory implements TransportFactory<Plane> {
    INSTANCE;

    private final IdDispenser idDispenser = TransportIdDispenser.INSTANCE;
    private final CommonService<Transport> service = TransportService.INSTANCE;

    @Override
    public Plane create(Object... args) {
        Plane plane = new Plane(idDispenser.getNextId(),
                (String) args[0],
                (String) args[1],
                (double) args[2],
                (double) args[3]);
        service.addToStorage(plane);
        return plane;
    }
}
