package com.crafties.cqrs.model.pet;

public class Pet {

    private final PetId petId;
    private final String name;
    private final PetType type;

    public Pet(PetId petId, String name, PetType type) {
        this.petId = petId;
        this.name = name;
        this.type = type;
    }

    public String name() {
        return name;
    }

    public PetType type() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (!petId.equals(pet.petId)) return false;
        if (!name.equals(pet.name)) return false;
        return type == pet.type;

    }

    @Override
    public int hashCode() {
        int result = petId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
