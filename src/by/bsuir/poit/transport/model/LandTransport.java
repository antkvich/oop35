package by.bsuir.poit.transport.model;

import java.util.Objects;

public abstract class LandTransport extends Transport {
    private final int wheelsCount;

    public LandTransport(long id, String color, String model, int wheelsCount) {
        super(id, color, model);
        this.wheelsCount = wheelsCount;
    }

    public int getWheelsCount() {
        return wheelsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LandTransport that = (LandTransport) o;
        return wheelsCount == that.wheelsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wheelsCount);
    }

    @Override
    public String toString() {
        return "wheels count: " + wheelsCount + "; ";
    }
}
