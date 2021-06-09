package by.bsuir.poit.transport.model.impl;

import by.bsuir.poit.transport.model.AirTransport;

import java.util.Objects;

public class Helicopter extends AirTransport {
    private static final long serialVersionUID = 4077030051630748416L;
    private final double bladesTwist;

    public Helicopter(long id, String color, String model, double rangeOfFlight, double bladesTwist) {
        super(id, color, model, rangeOfFlight);
        this.bladesTwist = bladesTwist;
    }

    public Helicopter() {
        super(0, "", "", 0);
        this.bladesTwist = 0;
    }

    public double getBladesTwist() {
        return bladesTwist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Helicopter that = (Helicopter) o;
        return Double.compare(that.bladesTwist, bladesTwist) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bladesTwist);
    }

    @Override
    public String toString() {
        return super.toString() +
                " blades twist: " + bladesTwist;
    }
}
