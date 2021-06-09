package by.bsuir.poit.transport.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public abstract class Transport implements Serializable {
    private static final long serialVersionUID = 1563884983206731021L;
    private final long id;
    private String color;
    private final String model;

    public Transport(long id, String color, String model) {
        this.id = id;
        this.color = color;
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return id == transport.id && Objects.equals(color, transport.color) && Objects.equals(model, transport.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, model);
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
