package by.bsuir.poit.transport.processing;

import by.bsuir.poit.transport.util.ExtensionLoader;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;

public enum HandleManager {
    INSTANCE;

    private final ExtensionLoader<Handler> extensionLoader;
    private static final String PLUGINS_PATH = "lib/handle";

    HandleManager() {
        extensionLoader = new ExtensionLoader<>(PLUGINS_PATH, Handler.class);
    }

    public ExtensionLoader<Handler> getExtensionLoader() {
        return extensionLoader;
    }

    public String handleBeforeSaving(String handlerType, String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument - null");
        }
        Optional<Handler> handlerInstance = fetchHandlerInstance(handlerType);
        return handlerInstance
                .map(handler -> handler.preprocess(data))
                .orElse(null);
    }

    public String handleAfterLoading(String handlerType, String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument - null");
        }
        Optional<Handler> handlerInstance = fetchHandlerInstance(handlerType);
        return handlerInstance
                .map(handler -> handler.postprocess(data))
                .orElse(null);
    }

    private Optional<Handler> fetchHandlerInstance(String handlerType) {
        Optional<Class<Handler>> handler = findHandlerClass(handlerType);
        return handler.map(this::extractHandler);
    }

    private Optional<Class<Handler>> findHandlerClass(String type) {
        if (type == null) {
            return Optional.empty();
        }
        List<Class<Handler>> handlerClasses = extensionLoader.getExtensionClasses();
        return handlerClasses.stream()
                .filter(handlerClass -> type.equals(handlerClass.getSimpleName()))
                .findFirst();
    }

    private Handler extractHandler(Class<Handler> handler) {
        try {
            Constructor<Handler> constructor = handler.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
