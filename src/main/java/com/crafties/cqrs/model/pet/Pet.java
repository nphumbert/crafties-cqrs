package com.crafties.cqrs.model.pet;

import com.crafties.cqrs.model.owner.OwnerId;

public class Pet {

    private final PetId id;
    private final String name;
    private final PetType type;
    private final OwnerId ownerId;

    public Pet(PetId petId, String name, PetType type, OwnerId ownerId) {
        this.id = petId;
        this.name = name;
        this.type = type;
        this.ownerId = ownerId;
    }

    public PetId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PetType getType() {
        return type;
    }

    public OwnerId getOwnerId() {
        return ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (!id.equals(pet.id)) return false;
        if (!name.equals(pet.name)) return false;
        return type == pet.type;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", ownerId=" + ownerId +
                '}';
    }
}
