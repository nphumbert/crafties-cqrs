package com.crafties.cqrs.facade.pets;

import com.crafties.cqrs.model.pet.PetType;

public class PetDto {

    private final String name;
    private final PetType type;
    private final String ownerName;

    public PetDto(String name, PetType type, String ownerName) {
        this.name = name;
        this.type = type;
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public PetType getType() {
        return type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetDto petDto = (PetDto) o;

        if (!name.equals(petDto.name)) return false;
        if (type != petDto.type) return false;
        return ownerName != null ? ownerName.equals(petDto.ownerName) : petDto.ownerName == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        return result;
    }
}
