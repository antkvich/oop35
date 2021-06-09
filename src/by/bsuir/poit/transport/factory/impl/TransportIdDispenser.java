package by.bsuir.poit.transport.factory.impl;


import by.bsuir.poit.transport.factory.IdDispenser;

import java.time.Instant;

public enum TransportIdDispenser implements IdDispenser {
    INSTANCE;

    @Override
    public Long getNextId() {
        return Instant.now().toEpochMilli();
    }
}
