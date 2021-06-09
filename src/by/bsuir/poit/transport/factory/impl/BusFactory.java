package by.bsuir.poit.transport.factory.impl;

import by.bsuir.poit.transport.factory.IdDispenser;
import by.bsuir.poit.transport.factory.TransportFactory;
import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.model.impl.Bus;
import by.bsuir.poit.transport.service.CommonService;
import by.bsuir.poit.transport.service.TransportService;

public enum BusFactory implements TransportFactory<Bus> {
    INSTANCE;

    private final IdDispenser idDispenser = TransportIdDispenser.INSTANCE;
    private final CommonService<Transport> service = TransportService.INSTANCE;

    @Override
    public Bus create(Object... args) {
        Bus bus = new Bus(idDispenser.getNextId(),
                (String) args[0],
                (String) args[1],
                (int) args[2],
                (int) args[3]);
        service.addToStorage(bus);
        return bus;
    }
}
