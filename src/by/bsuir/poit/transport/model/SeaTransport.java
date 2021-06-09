package by.bsuir.poit.transport.model;

import java.util.Objects;

public abstract class SeaTransport extends Transport {
    private final double length;

    public SeaTransport(long id, String color, String model, double length) {
        super(id, color, model);
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SeaTransport that = (SeaTransport) o;
        return Double.compare(that.length, length) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), length);
    }

    @Override
    public String toString() {
        return "length: " + length + "; ";
    }
}
