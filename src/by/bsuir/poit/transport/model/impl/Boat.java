package by.bsuir.poit.transport.model.impl;

import by.bsuir.poit.transport.model.SeaTransport;

import java.util.Objects;

public class Boat extends SeaTransport {
    private static final long serialVersionUID = 6540005212179264164L;
    private final double liftingCapacity;

    public Boat(long id, String color, String model, double length, double liftingCapacity) {
        super(id, color, model, length);
        this.liftingCapacity = liftingCapacity;
    }

    public Boat() {
        super(0, "", "", 0);
        this.liftingCapacity = 0;
    }

    public double getLiftingCapacity() {
        return liftingCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Boat boat = (Boat) o;
        return Double.compare(boat.liftingCapacity, liftingCapacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), liftingCapacity);
    }

    @Override
    public String toString() {
        return super.toString() +
                " lifting capacity: " + liftingCapacity;
    }
}
