package com.example.modulith.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class DatabaseObject<T> {
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(id, ((DatabaseObject<?>) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
