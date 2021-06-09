package by.bsuir.poit.transport.model.impl;

import by.bsuir.poit.transport.model.SeaTransport;

import java.util.Objects;

public class Ship extends SeaTransport {
    private static final long serialVersionUID = -4901314909511976324L;
    private final double displacement;

    public Ship(long id, String color, String model, double length, double displacement) {
        super(id, color, model, length);
        this.displacement = displacement;
    }

    public Ship() {
        super(0, "", "", 0);
        this.displacement = 0;
    }

    public double getDisplacement() {
        return displacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ship ship = (Ship) o;
        return Double.compare(ship.displacement, displacement) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), displacement);
    }

    @Override
    public String toString() {
        return super.toString() +
                " displacement: " + displacement;
    }
}
