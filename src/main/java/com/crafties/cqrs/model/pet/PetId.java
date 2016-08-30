package com.crafties.cqrs.model.pet;

import java.util.UUID;

public class PetId {
    private final Long value;

    public PetId(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetId petId = (PetId) o;

        return value.equals(petId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public static PetId generate() {
        return new PetId(UUID.randomUUID().getMostSignificantBits());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
