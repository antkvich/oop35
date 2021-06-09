package by.bsuir.poit.transport.model.impl;

import by.bsuir.poit.transport.model.LandTransport;

import java.util.Objects;

public class Car extends LandTransport {
    private static final long serialVersionUID = -1160583441799718571L;
    private final double accelerationTime;

    public Car(long id, String color, String model, int wheelsCount, double accelerationTime) {
        super(id, color, model, wheelsCount);
        this.accelerationTime = accelerationTime;
    }

    public Car() {
        super(0, "", "", 0);
        this.accelerationTime = 0;
    }

    public double getAccelerationTime() {
        return accelerationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return Double.compare(car.accelerationTime, accelerationTime) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accelerationTime);
    }

    @Override
    public String toString() {
        return super.toString() +
                " acceleration time: " + accelerationTime;
    }
}
