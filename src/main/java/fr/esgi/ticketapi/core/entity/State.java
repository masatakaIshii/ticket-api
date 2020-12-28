package fr.esgi.ticketapi.core.entity;

import java.io.Serializable;
import java.util.Objects;

public class State implements Serializable {
    private Integer id;
    private String name;

    public static final int REFUND = 2;
    public static final int KEEP = 1;

    public State(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(id, state.id) && Objects.equals(name, state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
