package by.bsuir.poit.transport.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Garage implements Serializable {
    private static final long serialVersionUID = -539781168534575688L;
    private final List<Transport> transports;

    @JsonCreator
    public Garage(@JsonProperty("transports") final List<Transport> transports) {
        this.transports = requireNonNull(transports);
    }

    public List<Transport> getTransports() {
        return transports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garage garage = (Garage) o;
        return Objects.equals(transports, garage.transports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transports);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "transport=" + transports +
                '}';
    }
}
