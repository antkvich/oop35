package by.bsuir.poit.transport.model.impl;

import by.bsuir.poit.transport.model.LandTransport;

import java.util.Objects;

public class Bus extends LandTransport {
    private static final long serialVersionUID = 6614726355541898024L;
    private final int passengerCapacity;

    public Bus(long id, String color, String model, int wheelsCount, int passengerCapacity) {
        super(id, color, model, wheelsCount);
        this.passengerCapacity = passengerCapacity;
    }

    public Bus() {
        super(0, "", "", 0);
        this.passengerCapacity = 0;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bus bus = (Bus) o;
        return passengerCapacity == bus.passengerCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengerCapacity);
    }

    @Override
    public String toString() {
        return super.toString() +
                " passenger capacity: " + passengerCapacity;
    }
}

