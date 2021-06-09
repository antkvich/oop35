package by.bsuir.poit.transport.model;

import java.util.Objects;

public abstract class AirTransport extends Transport {
    private double rangeOfFlight;

    public AirTransport(long id, String color, String model, double rangeOfFlight) {
        super(id, color, model);
        this.rangeOfFlight = rangeOfFlight;
    }

    public double getRangeOfFlight() {
        return rangeOfFlight;
    }

    public void setRangeOfFlight(double rangeOfFlight) {
        this.rangeOfFlight = rangeOfFlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AirTransport that = (AirTransport) o;
        return Double.compare(that.rangeOfFlight, rangeOfFlight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rangeOfFlight);
    }

    @Override
    public String toString() {
        return "range of flight: " + rangeOfFlight + "; ";
    }
}
