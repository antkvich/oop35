package by.bsuir.poit.transport.model.impl;

import by.bsuir.poit.transport.model.AirTransport;

import java.util.Objects;

public class Plane extends AirTransport {
    private static final long serialVersionUID = 5184354145303393873L;
    private final double wingspan;

    public Plane(long id, String color, String model, double rangeOfFlight, double wingspan) {
        super(id, color, model, rangeOfFlight);
        this.wingspan = wingspan;
    }

    public Plane() {
        super(0, "", "", 0);
        this.wingspan = 0;
    }

    public double getWingspan() {
        return wingspan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Double.compare(plane.wingspan, wingspan) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wingspan);
    }

    @Override
    public String toString() {
        return super.toString() +
                " wingspan: " + wingspan;
    }
}
