package by.bsuir.poit.transport.factory.impl;

import by.bsuir.poit.transport.factory.IdDispenser;
import by.bsuir.poit.transport.factory.TransportFactory;
import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.model.impl.Helicopter;
import by.bsuir.poit.transport.service.CommonService;
import by.bsuir.poit.transport.service.TransportService;

public enum HelicopterFactory implements TransportFactory<Helicopter> {
    INSTANCE;

    private final IdDispenser idDispenser = TransportIdDispenser.INSTANCE;
    private final CommonService<Transport> service = TransportService.INSTANCE;

    @Override
    public Helicopter create(Object... args) {
        Helicopter helicopter = new Helicopter(idDispenser.getNextId(),
                (String) args[0],
                (String) args[1],
                (double) args[2],
                (double) args[3]);
        service.addToStorage(helicopter);
        return helicopter;
    }
}
