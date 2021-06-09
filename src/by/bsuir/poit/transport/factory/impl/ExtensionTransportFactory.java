package by.bsuir.poit.transport.factory.impl;

import by.bsuir.poit.transport.factory.IdDispenser;
import by.bsuir.poit.transport.factory.TransportFactory;
import by.bsuir.poit.transport.model.Transport;
import by.bsuir.poit.transport.service.CommonService;
import by.bsuir.poit.transport.service.TransportService;
import by.bsuir.poit.transport.util.ExtensionLoader;

import java.lang.reflect.Constructor;
import java.util.List;

public enum ExtensionTransportFactory implements TransportFactory<Transport> {
    INSTANCE;

    private final IdDispenser idDispenser = TransportIdDispenser.INSTANCE;
    private final CommonService<Transport> service = TransportService.INSTANCE;
    private final ExtensionLoader<Transport> transportLoader;
    private static final String PLUGINS_PATH = "lib/transport";

    ExtensionTransportFactory() {
        transportLoader = new ExtensionLoader<>(PLUGINS_PATH, Transport.class);
    }

    public ExtensionLoader<Transport> getTransportLoader() {
        return transportLoader;
    }

    @Override
    public Transport create(Object... args) {
        String name = (String) args[0];
        String model = (String) args[1];
        String color = (String) args[2];
        List<Class<Transport>> transportClasses = transportLoader.getExtensionClasses();
        for (Class<Transport> transportClass : transportClasses) {
            if (!transportClass.getName().endsWith(name)) {
                continue;
            }
            try {
                Constructor<Transport> constructor =
                        transportClass.getConstructor(long.class, String.class, String.class);
                Long id = args.length > 3 ? (Long) args[3] : idDispenser.getNextId();
                Transport transport = constructor.newInstance(id, color, model);
                service.addToStorage(transport);
                return transport;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
