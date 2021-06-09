package by.bsuir.poit.transport.proxy;

import by.bsuir.poit.transport.exception.PropertiesReadingException;
import by.bsuir.poit.transport.serialization.CustomSerialization;
import by.bsuir.poit.transport.serialization.Serialization;
import by.bsuir.poit.transport.model.Garage;

import java.util.Optional;

public class CustomSerializationProxy implements Serialization<Garage>{

    private static CustomSerialization object;

    @Override
    public void serialize(Garage entity) throws PropertiesReadingException {
        if (object == null)
            object = new CustomSerialization();
        object.serialize(entity);
    }

    @Override
    public Optional<Garage> deserialize() throws PropertiesReadingException {
        if (object == null)
            object = new CustomSerialization();
        return object.deserialize();
    }
}
