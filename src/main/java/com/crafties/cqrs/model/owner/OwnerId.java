package com.crafties.cqrs.model.owner;

public class OwnerId {

    private final Long value;

    public OwnerId(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerId ownerId = (OwnerId) o;

        return value.equals(ownerId.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
