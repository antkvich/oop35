package by.bsuir.poit.transport.factory.impl;

import by.bsuir.poit.transport.factory.IdDispenser;
import by.bsuir.poit.transport.factory.TransportFactory;
import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.model.impl.Car;
import by.bsuir.poit.transport.service.CommonService;
import by.bsuir.poit.transport.service.TransportService;

public enum CarFactory implements TransportFactory<Car> {
    INSTANCE;

    private final IdDispenser idDispenser = TransportIdDispenser.INSTANCE;
    private final CommonService<Transport> service = TransportService.INSTANCE;

    @Override
    public Car create(Object... args) {
        Car car = new Car(idDispenser.getNextId(),
                (String) args[0],
                (String) args[1],
                (int) args[2],
                (double) args[3]);
        service.addToStorage(car);
        return car;
    }
}
